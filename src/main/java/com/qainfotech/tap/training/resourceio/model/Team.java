package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hamcrest.core.IsInstanceOf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.qainfotech.tap.training.resourceio.TeamsJsonReader;
import com.qainfotech.tap.training.resourceio.TeamsYamlReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {
    
    @Override
	public String toString() {
		return "Team [name=" + name + ", id=" + id + ", members=" + members + "]";
	}

	private final String name;
    private final Integer id;
    private final List<Individual> members;
   // private TeamsJsonReader teamJsonReader;
    
    /*public Team(JSONObject jsonObj){
       
    	Object object=teamMap.get("teams");
    	System.out.println("TEAM: "+object);
    	 //throw new UnsupportedOperationException("Not implemented.");
    	//TeamsJsonReader reader=
    	this.name=jsonObj.get("name").toString();
    	this.id=Integer.parseInt(jsonObj.get("id").toString());
    	this.members=new ArrayList<>();
    	List<Individual>arrayOfIndividuals=(new TeamsJsonReader()).getListOfIndividuals();
    	JSONArray memberArray=(JSONArray) jsonObj.get("members");
    	Iterator<Individual> itr=arrayOfIndividuals.iterator();
    	while(itr.hasNext()){
    		Individual individual=itr.next();
    		for(int i=0;i<memberArray.size();i++){
    			if(individual.getId()==Integer.parseInt(memberArray.get(i).toString())){
    				members.add(individual);
    			}
    		}
    	}
    	
    }*/
    
 /*   public Team(Map<String,Object> teamMap){
    	System.out.println(teamMap);
    	this.id=Integer.parseInt(teamMap.get("id").toString());
    	this.name=teamMap.get("name").toString();
    	this.members=new ArrayList<>();
    	List<Individual>arrayOfIndividuals=(new TeamsJsonReader()).getListOfIndividuals();
    	Object object= teamMap.get("members");
    	
    	if(object instanceof JSONArray){
    		
			JSONArray memberArray = (JSONArray) teamMap.get("members");
			Iterator<Individual> itr = arrayOfIndividuals.iterator();
			while (itr.hasNext()) {
				Individual individual = itr.next();
				for (int i = 0; i < memberArray.size(); i++) {
					if (individual.getId() == Integer.parseInt(memberArray.get(i).toString())) {
						this.members.add(individual);
					}
				}
			}
    	}
    	else if(object instanceof List){
    		List memberIdList=(List) object;
    		for(Object obj : memberIdList){
    			int id=Integer.parseInt(obj.toString());
    			System.out.println((new TeamsYamlReader()).getIndividualById(id));
    		}
    		
    		System.out.println("not  a instance");
    	}
    	
    	
    }*/
    public Team(Map<String,Object> teamMap){
    	/*System.out.println(teamMap);*/
     
    	this.id=Integer.parseInt(teamMap.get("id").toString());
    	this.name=teamMap.get("name").toString();
    	this.members=(List<Individual>) teamMap.get("members");
    	
    	
    }
    
    /**
     * get team name
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * get team id
     * 
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /** 
     * get list of individuals that are members of this team
     * 
     * @return 
     */
    public List<Individual> getMembers(){
        return members;
    }
    
    /**
     * get a list of individuals that are members of this team and are also active
     * 
     * @return 
     */
    public List<Individual> getActiveMembers(){
        //throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> activeMembersOfTeam=new ArrayList<>();
    	Iterator<Individual> itr=this.members.iterator();
    	while(itr.hasNext()){
    		Individual individual=itr.next();
    		if(individual.isActive()){
    			activeMembersOfTeam.add(individual);
    		}
    	}
    	return activeMembersOfTeam;
    }
        
    /**
     * get a list of individuals that are members of this team but are inactive
     * 
     * @return 
     */
    public List<Individual> getInactiveMembers(){
       // throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> inactiveMembersListOfTeam=new ArrayList<>();
    	Iterator<Individual>itr=this.members.iterator();
    	while(itr.hasNext()){
    		Individual individual=itr.next();
    		if(!(individual.isActive())){
    			inactiveMembersListOfTeam.add(individual);
    		}
    	}
    	return inactiveMembersListOfTeam;
    }
}
