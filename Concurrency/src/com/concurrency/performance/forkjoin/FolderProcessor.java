package com.concurrency.performance.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinTask:
 * 
 * This is an abstract class for creating tasks that run within a ForkJoinPool.
 * The Recursiveaction and RecursiveTask are the only two direct, known
 * subclasses of ForkJoinTask. The only difference between these two classes is
 * that the RecursiveAction does not return a value while RecursiveTask does
 * have a return value and returns an object of specified type.
 * 
 * In both cases, you would need to implement the compute method in your
 * subclass that performs the main computation desired by the task. The
 * ForkJoinTask class provides several methods for checking the execution status
 * of a task. The isDone() method returns true if a task completes in any way.
 * The isCompletedNormally() method returns true if a task completes without
 * cancellation or encountering an exception, and isCancelled() returns true if
 * the task was cancelled. Lastly, isCompletedabnormally().
 * 
 * @author Administrator
 * 
 */
public class FolderProcessor extends RecursiveTask<List<String>> {
	private static final long serialVersionUID = 1L;
	// This attribute will store the full path of the folder this task is going
	// to process.
	private final String path;
	// This attribute will store the name of the extension of the files this
	// task is going to look for.
	private final String extension;

	// Implement the constructor of the class to initialize its attributes
	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	// Implement the compute() method. As you parameterized the RecursiveTask
	// class with the List<String> type,
	// this method has to return an object of that type.
	@Override
	protected List<String> compute() {
		// List to store the names of the files stored in the folder.
		List<String> list = new ArrayList<String>();
		// FolderProcessor tasks to store the subtasks that are going to process
		// the subfolders stored in the folder
		List<FolderProcessor> tasks = new ArrayList<FolderProcessor>();
		// Get the content of the folder.
		File file = new File(path);
		File content[] = file.listFiles();
		// For each element in the folder, if there is a subfolder, create a new
		// FolderProcessor object
		// and execute it asynchronously using the fork() method.
		if (content != null) {
			for (int i = 0; i < content.length; i++) {
				if (content[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(
							content[i].getAbsolutePath(), extension);
					task.fork(); //This method sends the task to the pool that will execute it if it has a free worker-thread or it can create a new one. 
					tasks.add(task);
				}
				// Otherwise, compare the extension of the file with the
				// extension you are looking for using the checkFile() method
				// and, if they are equal, store the full path of the file in
				// the list of strings declared earlier.
				else {
					if (checkFile(content[i].getName())) {
						list.add(content[i].getAbsolutePath());
					}
				}
			}
		}
		// If the list of the FolderProcessor subtasks has more than 50
		// elements,
		// write a message to the console to indicate this circumstance.
		if (tasks.size() > 50) {
			System.out.printf("%s: %d tasks ran.\n", file.getAbsolutePath(),
					tasks.size());
		}
		// add to the list of files the results returned by the subtasks
		// launched by this task.
		addResultsFromTasks(list, tasks);
		// Return the list of strings
		return list;
	}

	// For each task stored in the list of tasks, call the join() method that
	// will wait for its finalization and then will return the result of the
	// task.
	// Add that result to the list of strings using the addAll() method.
	private void addResultsFromTasks(List<String> list,
			List<FolderProcessor> tasks) {
		for (FolderProcessor item : tasks) {
			list.addAll(item.join());//This method called in a task waits for the finalization of its execution and returns the value returned by the compute() method.
		}
	}

	// This method compares if the name of a file passed as a parameter ends
	// with the extension you are looking for.
	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}
}