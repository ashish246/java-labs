package rest.reflection.entity;


public class ManifestRO implements IManifest {
	private String mManifestId;

	private String mOrderReference;

	private String mContractId;

	public ManifestRO() {
	}

	@Override
	public String getContractId() {
		return mContractId;
	}

	@Override
	public void setContractId(String pContractId) {
		mContractId = pContractId;
	}

	@Override
	public String getManifestId() {
		return mManifestId;
	}

	@Override
	public void setManifestId(String pManifestId) {
		mManifestId = pManifestId;
	}

	/**
	 * @return the mOrderReference
	 */
	@Override
	public String getOrderReference() {
		return mOrderReference;
	}

	/**
	 * @param mOrderReference
	 *            the mOrderReference to set
	 */
	@Override
	public void setOrderReference(String pOrderReference) {
		this.mOrderReference = pOrderReference;
	}

}
