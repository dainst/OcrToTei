package de.uni_koeln.arachne.reader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {

	private String directoryPath;
	private List<String> filePathList;
	
	public DirectoryReader(String directoryPath) {
		this.directoryPath = directoryPath;
		this.filePathList = new ArrayList<String>();
	}
	
	public void read() {
		File dir = new File(this.directoryPath);
		if(dir.isDirectory()) {
			FilenameFilter filter = new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					if(!name.startsWith(".") ) {
						return true;
					}
					return false;
				}
			};
			
			String[] filelist = dir.list(filter);
			for (int i = 0; i < filelist.length; i++) {
				this.filePathList.add(this.directoryPath + "/" + filelist[i]);
			}
		}
	}
	
	public List<String> getFilePathList() {
		return this.filePathList;
	}
}
