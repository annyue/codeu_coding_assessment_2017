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


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

final class MyJSON implements JSON {
	HashMap<String, ArrayList<JSON>> obj;

	
	public MyJSON(){
		obj = new HashMap<String,ArrayList<JSON>>();
	}
	
  @Override
  public JSON getObject(String name) {
	JSON result = new MyJSON();
	if(!obj.containsKey(name)){
		return null;
	}
	JSON val = obj.get(name).get(0);
	result.setObject(name, val);
    return result;
  }

  @Override
  public JSON setObject(String name, JSON value) {
	ArrayList<JSON> vals = obj.get(name);
	vals.add(value);
	obj.put(name, vals);
    return this;
  }

  @Override
  public String getString(String name) {
	if(!obj.containsKey(name)){
		return null;
	}
	ArrayList<JSON> result = obj.get(name);
	return result.toString();   
  }

  @Override
  public JSON setString(String name, String value) {
	ArrayList<JSON> thing = obj.get(name);
	obj.remove(name);
	obj.put(value, thing);
    return this;
  }

  @Override
  public void getObjects(Collection<String> names) {
    for(String s: obj.keySet()){
    	names.add(obj.get(s).toString());
    }
  }

  @Override
  public void getStrings(Collection<String> names) {
    for(String s: obj.keySet()){
    	names.add(s);
    }
  }
}
