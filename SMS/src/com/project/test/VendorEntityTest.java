/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */
package com.project.test;

import com.project.rest.providers.VendorEntity;

import junit.framework.TestCase;

/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */
public class VendorEntityTest extends TestCase {

	public void testGenerateId() {
		
		String id = System.currentTimeMillis() + "";
		VendorEntity entity = new VendorEntity(id,"user");
				
		assertFalse(entity.getId().equals(""));
	}

	public void testSetName() {
		
		String id = System.currentTimeMillis() + "";
		VendorEntity entity = new VendorEntity(id,"");
		entity.setName("SMS user");
		
		assertFalse(entity.getName().equals(""));
	}
	
}
