package au.edu.sydney.brawndo.erp.spfea.ordering;

import au.edu.sydney.brawndo.erp.ordering.Order;
import au.edu.sydney.brawndo.erp.ordering.Product;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Discount type 2  --Discount2
 * achieve
 * getTotalCost
 * generateInvoiceData
 * shortDesc
 * longDesc
 * setOrder
 * method
 */
@SuppressWarnings("Duplicates")
public class Discount2 implements Discount {
    private DiscountBean bean;
    private Order order;
    public Discount2(DiscountBean bean,Order order){
        this.bean = bean;
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setBean(DiscountBean bean) {
        this.bean = bean;
    }

    public DiscountBean getBean() {
        return this.bean;
    }

    @Override
    public double getTotalCost() {
        double cost = 0.0;
        if(bean.isBusiness()){
            for (Product product: order.getAllProducts()) {
                int count = order.getProductQty(product);
                if (count >= this.getBean().getDiscountThreshold()) {
                    cost +=  count * product.getCost() * this.getBean().getDiscountRate();
                } else {
                    cost +=  count * product.getCost();
                }
            }
        }else{
            for (Product product: order.getAllProducts()) {
                int count = order.getProductQty(product);
                if (count >= bean.getDiscountThreshold()) {
                    cost +=  count * product.getCost() * bean.getDiscountRate();
                } else {
                    cost +=  count * product.getCost();
                }
            }
        }

        if(bean.isSubscription()){
            cost = cost * bean.getNumShipments();
        }
        return cost;
    }

    @Override
    public String generateInvoiceData() {
        if(bean.isSubscription()){
            if(bean.isBusiness()){
                return String.format("Your business account will be charged: $%,.2f each week, with a total overall cost of: $%,.2f" +
                        "\nPlease see your Brawndo© merchandising representative for itemised details.", getTotalCost()/bean.getNumShipments(), getTotalCost());
            }else{
                StringBuilder sb = new StringBuilder();

                sb.append("Thank you for your Brawndo© order!\n");
                sb.append("Your order comes to: $");
                sb.append(String.format("%,.2f", getTotalCost()/bean.getNumShipments()));
                sb.append(" each week, with a total overall cost of: $");
                sb.append(String.format("%,.2f", getTotalCost()));
                sb.append("\nPlease see below for details:\n");
                List<Product> keyList = new ArrayList<>(order.getAllProducts());
                keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

                for (Product product: keyList) {
                    sb.append("\tProduct name: ");
                    sb.append(product.getProductName());
                    sb.append("\tQty: ");
                    sb.append(order.getProductQty(product));
                    sb.append("\tCost per unit: ");
                    sb.append(String.format("$%,.2f", product.getCost()));
                    sb.append("\tSubtotal: ");
                    sb.append(String.format("$%,.2f\n", product.getCost() * order.getProductQty(product)));
                }

                return sb.toString();

            }
        }else{
            if(!bean.isBusiness()){
                StringBuilder sb = new StringBuilder();

                sb.append("Thank you for your Brawndo© order!\n");
                sb.append("Your order comes to: $");
                sb.append(String.format("%,.2f", getTotalCost()));
                sb.append("\nPlease see below for details:\n");
                List<Product> keyList = new ArrayList<>(order.getAllProducts());
                keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

                for (Product product: keyList) {
                    sb.append("\tProduct name: ");
                    sb.append(product.getProductName());
                    sb.append("\tQty: ");
                    sb.append(order.getProductQty(product));
                    sb.append("\tCost per unit: ");
                    sb.append(String.format("$%,.2f", product.getCost()));
                    sb.append("\tSubtotal: ");
                    sb.append(String.format("$%,.2f\n", product.getCost() * order.getProductQty(product)));
                }
                return sb.toString();
            }else{
                return String.format("Your business account has been charged: $%,.2f" +
                        "\nPlease see your Brawndo© merchandising representative for itemised details.", getTotalCost());
            }
        }

    }

    @Override
    public String shortDesc() {
        if(bean.isSubscription()){
            return String.format("ID:%s $%,.2f per shipment, $%,.2f total", bean.getId(), getTotalCost()/bean.getNumShipments(), getTotalCost()/bean.getNumShipments());
        }else{
            return String.format("ID:%s $%,.2f", this.getBean().getId(), getTotalCost());
        }
    }

    @Override
    public String longDesc() {
        if(bean.isSubscription()){
            double fullCost = 0.0;
            double discountedCost = getTotalCost();
            StringBuilder productSB = new StringBuilder();

            List<Product> keyList = new ArrayList<>(order.getAllProducts());
            keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

            for (Product product: keyList) {
                double subtotal = product.getCost() * order.getProductQty(product);
                fullCost += subtotal;

                productSB.append(String.format("\tProduct name: %s\tQty: %d\tUnit cost: $%,.2f\tSubtotal: $%,.2f\n",
                        product.getProductName(),
                        order.getProductQty(product),
                        product.getCost(),
                        subtotal));
            }

            return String.format(false ? "" : "*NOT FINALISED*\n" +
                            "Order details (id #%d)\n" +
                            "Date: %s\n" +
                            "Number of shipments: %d\n" +
                            "Products:\n" +
                            "%s" +
                            "\tDiscount: -$%,.2f\n" +
                            "Recurring cost: $%,.2f\n" +
                            "Total cost: $%,.2f\n",
                    this.getBean().getId(),
                    this.getBean().getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    this.getBean().getNumShipments(),
                    productSB.toString(),
                    fullCost - discountedCost/bean.getNumShipments(),
                    discountedCost/bean.getNumShipments(),
                    getTotalCost()
            );
        }else{
            double fullCost = 0.0;
            double discountedCost = getTotalCost();
            StringBuilder productSB = new StringBuilder();

            List<Product> keyList = new ArrayList<>(order.getAllProducts());
            keyList.sort(Comparator.comparing(Product::getProductName).thenComparing(Product::getCost));

            for (Product product: keyList) {
                double subtotal = product.getCost() * order.getProductQty(product);
                fullCost += subtotal;

                productSB.append(String.format("\tProduct name: %s\tQty: %d\tUnit cost: $%,.2f\tSubtotal: $%,.2f\n",
                        product.getProductName(),
                        order.getProductQty(product),
                        product.getCost(),
                        subtotal));
            }

            return String.format(false ? "" : "*NOT FINALISED*\n" +
                            "Order details (id #%d)\n" +
                            "Date: %s\n" +
                            "Products:\n" +
                            "%s" +
                            "\tDiscount: -$%,.2f\n" +
                            "Total cost: $%,.2f\n",
                    this.getBean().getId(),
                    this.getBean().getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                    productSB.toString(),
                    fullCost - discountedCost,
                    discountedCost
            );
        }
    }
}
