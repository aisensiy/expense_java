package com.tw.api;

import com.tw.domain.Policy;
import com.tw.mapper.PolicyMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

public class PoliciesApi {
    private int categoryId;
    private PolicyMapper policyMapper;

    public PoliciesApi(int categoryId, PolicyMapper policyMapper) {

        this.categoryId = categoryId;
        this.policyMapper = policyMapper;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPolicy(Form form) {
        Policy policy = createPolicyFromForm(form);
        policyMapper.createPolicy(categoryId, policy);
        return Response.status(201).build();
    }

    private Policy createPolicyFromForm(Form form) {
        MultivaluedMap<String, String> map = form.asMap();
        return new Policy(Integer.parseInt(map.getFirst("maxAmount")), Timestamp.valueOf(map.getFirst("createdAt")));
    }
}
