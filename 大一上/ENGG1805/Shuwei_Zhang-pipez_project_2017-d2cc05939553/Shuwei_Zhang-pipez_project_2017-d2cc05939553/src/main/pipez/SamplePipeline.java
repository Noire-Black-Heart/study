package pipez;

import pipez.core.Pipeline;
import pipez.io.CSVReader;
import pipez.io.CSVWriter;
import pipez.IdentityPipe;

public class SamplePipeline {

	public static void main(String[] args) {
		ValueMatchPipe select = ValueMatchPipe.create("1002");
		Pipeline.create("Sample Workflow") // create a new pipeline called "Sample Workflow"
	
			.in(CSVReader.from("in/sample.csv")) // set the pipeline to read as input 
												 // the CSV file "in/sample.csv"
			
			.append(IdentityPipe.create()) // create a pipe and append it to the pipeline; 
			.append(ReversePipe.create()) // append another pipe to the pipeline; 
			.append(OddFieldsPipe.create()) // and another one; you get the idea of 
											  // how to add pipes

			.out(CSVWriter.to("out/sample_out.csv")); // finally, we write the result of the 
													  // pipeline to the CSV file "out/sample_out.csv"
		
	}
}