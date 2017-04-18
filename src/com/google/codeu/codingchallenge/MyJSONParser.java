// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


final class MyJSONParser implements JSONParser {
	
	@Override
	public JSON parse(String in) throws IOException {
		if(in.charAt(0) != ('{') || in.charAt(in.length()-1) != ('}')){
			throw new IOException("Not a valid JSON object");
		}
		JSON result = new MyJSON();
		in = in.replaceAll("\\s+", "");
		ArrayList<String> split = splitString(in);
		ArrayList<String> toAdd = new ArrayList<String>();
		for(String s: split){
			int valid = isValidString(s);
			if(valid == 1){
				toAdd.add(s);
			}
			
		}
			


		
		return result;
	}
	
	/*
	 * splits the current string based on where the ':' is. Ideally would have used recursion but ran out
	 * of time. 
	 */
	private ArrayList<String> splitString(String in){
		if(in.charAt(0) == '{' && in.charAt(in.length()-1) == '}'){
			in = in.substring(1, in.length()-1);
		}
		ArrayList<String> result = new ArrayList<String>();
		String start = "";
		String end = "";
		int index = 0;
		while(index < in.length()){
			if(in.charAt(index) == ':'){
				start = in.substring(0, index);
				end = in.substring(index+1);
			}
			index++;
		}
		result.add(start);
		result.add(end);
		return result;
	} 
	/*
	 * 0 - false
	 * 1 - true
	 * 2 - false but needs to be split again
	 * checks whether the input is a valid String
	 */
	private int isValidString(String in){
		if(in.charAt(0) == '{' || in.charAt(in.length()-1) == '}'){
			return 2;
		}
		if(in.charAt(0) == '\"'){
			int index = 0;
			int numQuotes = 0;
			while(index < in.length()){
				if(in.charAt(index) == '\\'){
					if(in.charAt(index+1) != 'n' || in.charAt(index+1) != 't' || in.charAt(index+1) != '"'){
						return 0;
					}
				}else if(in.charAt(index) == '"'){
					numQuotes++;
				}
				index++;
			}
			if(numQuotes % 2 != 0){
				return 0;
			}
		}
		return 1;
	}
	
}
