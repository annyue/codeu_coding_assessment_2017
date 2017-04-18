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


import java.util.Collection;
import java.util.HashMap;

final class MyJSON implements JSON {
	private HashMap<String, String> stringVals;
	private HashMap<String, JSON> jsonVals;

	
	public MyJSON(){
		stringVals = new HashMap<String, String>();
		jsonVals = new HashMap<String, JSON>();
	}
	
  @Override
  public JSON getObject(String name) {
	if(name.length() == 0 || name == null){
		throw new IllegalArgumentException("Must be valid input");
	}
	return jsonVals.get(name);
  }

  @Override
  public JSON setObject(String name, JSON value) {
	jsonVals.put(name, value);
    return this;
  }

  @Override
  public String getString(String name) {
	return stringVals.get(name);  
  }

  @Override
  public JSON setString(String name, String value) {
	stringVals.put(name, value);
	return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
    for(String j: jsonVals.keySet()){
    	names.add(j);
    }
  }

  @Override
  public void getStrings(Collection<String> names) {
    for(String s: stringVals.keySet()){
    	names.add(s);
    }
  }
}
