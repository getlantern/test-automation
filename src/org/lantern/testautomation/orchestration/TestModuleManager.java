package org.lantern.testautomation.orchestration;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.lantern.testautomation.data.TestModuleData;
import org.lantern.testautomation.module.TestModule;

public class TestModuleManager {

	public boolean runModule(TestModuleData testModuleData) {
		boolean mResult = true;
		
		HashMap<String,String> parameters = (HashMap<String,String>)testModuleData.getContent();
		TestModule module = getModuleInstance(testModuleData.getName());
		mResult = module.runModule(parameters);
		
		testModuleData.setResult(mResult);
		return mResult;
	}
	
	TestModule getModuleInstance(String name){
		TestModule module = null;
		
		name = "org.lantern.testautomation.module."+name;
		try{
			Class<?> clazz = Class.forName(name);
			Constructor<?> ctor = clazz.getConstructors()[0];
			Object object = ctor.newInstance();
			
			module = (TestModule) object;
		}
		catch(Exception e){
			//TODO: do something
		}
		
		return module;
	}

}
