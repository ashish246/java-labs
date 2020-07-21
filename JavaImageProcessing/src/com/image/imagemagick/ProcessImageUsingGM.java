package com.image.imagemagick;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.gm4java.engine.GMConnection;
import org.gm4java.engine.GMException;
import org.gm4java.engine.GMService;
import org.gm4java.engine.GMServiceException;
import org.gm4java.engine.support.SimpleGMService;
import org.gm4java.im4java.GMBatchCommand;

public class ProcessImageUsingGM {

	public static void main(String[] args) {

		Path tSourceImage = Paths.get("src/com/image/data/Org.jpg");
		
		/*GMConnectionPoolConfig tConfig = new GMConnectionPoolConfig();
		tConfig.setMaxActive(10);
		tConfig.setMaxIdle(10);
		tConfig.setMaxWait(1L);
		tConfig.setMinIdle(2);
		
		GMService service = new PooledGMService(tConfig);*/
		
		GMService service = new SimpleGMService();
		
		//service.execute(command, arguments);
		
		GMBatchCommand command = new GMBatchCommand(service, "convert");
		// create the operation, add images and operators/options
		/*IMOperation op = new IMOperation();
		op.addImage("input.jpg");
		op.resize(800, 600);
		op.addImage("output.jpg");
		// execute the operation
		command.run(op);*/
		
		/*GMBatchCommand command = new GMBatchCommand(service, "identify");
		IMOperation op = new IMOperation();
		op.ping();
		final String format = "%m\n%W\n%H\n%g\n%z";
		op.format(format);
		op.addImage();
		ArrayListOutputConsumer output = new ArrayListOutputConsumer();
		command.setOutputConsumer(output);
		command.run(op, SOURCE_IMAGE);
		ArrayList<String> cmdOutput = output.getOutput();*/
		
		String result = null;
		try {
			
			GMConnection connection = service.getConnection();
	
			try {
	
				  result = connection.execute("convert", tSourceImage.getFileName().toString());
	
			} catch (GMException e) {
				e.printStackTrace();
			} finally {
			      connection.close();
			}
		} catch (GMServiceException e) {
			e.printStackTrace();
		}
	}
}
