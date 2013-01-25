/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */

package com.project.rest.providers;

import org.sakaiproject.entitybus.entityprovider.annotations.EntityDateCreated;
import org.sakaiproject.entitybus.entityprovider.annotations.EntityFieldRequired;
import org.sakaiproject.entitybus.entityprovider.annotations.EntityId;
import org.sakaiproject.entitybus.entityprovider.annotations.EntityLastModified;

/**
 * @author Shreshth Sandilya (shreshth.4@gmail.com)
 */
public class VendorEntity {

    @EntityId private String id;
    @EntityFieldRequired private String name;
    private boolean poCreated;
    private int poNumber;
    public String poDetails;
    private String poType;
    

    /**
     * Basic empty constructor
     */
    protected VendorEntity() { }

    public VendorEntity(String id, String name) {
        this(id, name, 0);
    }

    public VendorEntity(String name, int poNumber) {
        this(null, name, poNumber);
    }

    public VendorEntity(String id, String name, int poNumber) {
        this.id = id;
        this.name = name;
        this.poNumber = poNumber;        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    

    /**
     * @return a copy of this object
     */
    public VendorEntity copy() {
        return copy(this);
    }

    /**
     * @return a copy of the supplied object
     */
    public static VendorEntity copy(VendorEntity me) {
        if (me == null) {
            throw new IllegalArgumentException("entity to copy must not be null");
        }
        VendorEntity togo = new VendorEntity(me.id, me.name, me.poNumber);
        togo.poDetails = me.poDetails;
        return togo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VendorEntity other = (VendorEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "id:" + this.id + ", name:" + this.name + ", poNumber:" + poNumber;
    }

	public boolean isPoCreated() {
		return poCreated;
	}

	public void setPoCreated(boolean poCreated) {
		this.poCreated = poCreated;
	}

	public String getPoDetails() {
		return poDetails;
	}

	public void setPoDetails(String poDetails) {
		this.poDetails = poDetails;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

}
