import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class TransactionTest {

	@BeforeClass
	void createGenesis() {
		Block gene = new Block();
		byte[] array = new byte[32];
		gene.setPreviousHash(array);
	}
	
	
	@Test
	void test() {
		Block bloc = new Block();
		
		byte[] array = new byte[32];
		array[0] = 0;
		bloc.setPreviousHash(array);
		Transaction trans1 = new Transaction();
		trans1.setSender("szha3901safasf");
		trans1.setContent("cyka blyatasfdsgfadsguhagauighuihagiaudhsgihadsighighiugiudasgoig");
		System.out.println("this transaction is 1 " + trans1.toString());
		
		//kDOC3KtL2AKgrg07XdYw/xGT9plH351lPenCMHinL5U=
		
		bloc.getTransactions().add(trans1);
		
		System.out.println("this is bloc 1 hash "+ Base64.getEncoder().encodeToString(bloc.calculateHash()));
		
		Block bloc2 = new Block();
		
		bloc2.setPreviousBlock(bloc);
		bloc2.setPreviousHash(bloc2.getPreviousBlock().calculateHash());
		System.out.println("this is hash of bloc 1 by bloc2 " + Base64.getEncoder().encodeToString(bloc2.getPreviousBlock().calculateHash()));
		
		
	}
	
	@Test
	void BlockChainTest() {
		
	}
	
	

}
