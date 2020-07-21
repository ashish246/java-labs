package rest.reflection.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public interface IManifest
{
    @JsonProperty("order_id")
    public String getManifestId();

    @JsonProperty("order_id")
    public void setManifestId(String pLocationId);

    @JsonProperty("order_reference")
    public String getOrderReference();

    @JsonProperty("order_reference")
    public void setOrderReference(String pOrderReference);

    @JsonProperty("contract_id")
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public String getContractId();

    @JsonProperty("contract_id")
    public void setContractId(String pLocationId);

}
