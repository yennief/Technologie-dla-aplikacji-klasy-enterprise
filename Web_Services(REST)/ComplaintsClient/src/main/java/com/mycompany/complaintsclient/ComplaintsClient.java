/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.complaintsclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author justyna
 */
public class ComplaintsClient {

    public static void main(String[] args) {
          Client client = ClientBuilder.newClient();
        String complaints = client.target("http://localhost:8080/Complaints/" + "resources/complaints/")
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        
        String openComplaint = client.target("http://localhost:8080/Complaints/" + "resources/complaints/757")
            .request(MediaType.APPLICATION_JSON)
            .get(String.class);
        
        JsonReader reader = Json.createReader(new StringReader(complaints));
        JsonArray complaintsJsonArray = reader.readArray();

        for (int i = 0; i < complaintsJsonArray.size(); i++) {
            JsonObject complaintJsonObject = complaintsJsonArray.getJsonObject(i);
            String complaint = complaintJsonObject.getString("complaintText");
            System.out.println("Complaint " + complaintJsonObject.getInt("id") + " by " + complaintJsonObject.getString("author") + " (" + complaintJsonObject.getString("status") + "): " + complaintJsonObject.getString("complaintText"));
        }
        
        System.out.println("All complaints: " + complaints);
        System.out.println("Some closed complaint: " + complaintsJsonArray.getJsonObject(0));
        
        JsonObject someOpenComplaint = complaintsJsonArray.getJsonObject(0);
        JsonObjectBuilder updatedComplaintBuilder = Json.createObjectBuilder();
        updatedComplaintBuilder.add("id", someOpenComplaint.getInt("id"));
        updatedComplaintBuilder.add("author", someOpenComplaint.getString("author"));
        updatedComplaintBuilder.add("complaintText", someOpenComplaint.getString("complaintText"));
        updatedComplaintBuilder.add("complaintDate", someOpenComplaint.getString("complaintDate"));
        updatedComplaintBuilder.add("status", "closed"); 
        
        JsonObject updatedComplaint = updatedComplaintBuilder.build();

        Response response = client.target("http://localhost:8080/Complaints/" + "resources/complaints/" + someOpenComplaint.getInt("id"))
                        .request()
                        .put(Entity.entity(updatedComplaint.toString(), MediaType.APPLICATION_JSON));
        if (response.getStatus() == 204) {
            System.out.println("Complaint " + someOpenComplaint.getInt("id") + " status changed to 'closed'");
}       else {
            System.out.println("Failed to update complaint " + response.getStatus());
            }
    }
    }
}
