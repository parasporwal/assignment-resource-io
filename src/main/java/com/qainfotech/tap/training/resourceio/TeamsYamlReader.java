package com.qainfotech.tap.training.resourceio;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.simple.JSONArray;
import org.yaml.snakeyaml.Yaml;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

public class TeamsYamlReader {
	private InputStream instream;
	private Map<String,List> yamlData;
	private List<Individual> individualList;
	private Individual individual;
	private Team team;



	public TeamsYamlReader(){
		Yaml yaml=new Yaml();
		instream=this.getClass().getClassLoader().getResourceAsStream("db.yaml");
		yamlData=(Map<String, List>) yaml.load(instream);
		//individualList=new ArrayList<>();

	}

	public List<Individual> getListOfIndividuals(){
		individualList=new ArrayList<>(); 
		List<Map<String,Object>>  individualMapList= yamlData.get("individuals");
		for(Map<String, Object> map : individualMapList){
			individual=new Individual(map);
			individualList.add(individual);
		}

		return individualList;

		// throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * get individual object by id
	 * 
	 * @param id individual id
	 * @return 
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
		individualList=getListOfIndividuals();
		for(Individual individual : individualList){
			if(individual.getId()==id.intValue()){
				return individual;
			}
		}
		//System.out.println(individualList);
		throw new ObjectNotFoundException("Individual Object with id=100 not found", "Id", id.toString());
		//throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return 
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException{
		//  throw new UnsupportedOperationException("Not implemented.");
		individualList=getListOfIndividuals();
		for(Individual individual : individualList){
			if(individual.getName().equalsIgnoreCase(name)){
				return individual;
			}
		}
		throw new ObjectNotFoundException("object not found", "name", name);
	}


	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals(){
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> listOfInactiveIndividuals=new ArrayList<>();
		individualList=getListOfIndividuals();
		for(Individual individual : individualList){
			if(!individual.isActive()){
				listOfInactiveIndividuals.add(individual);

			}
		}
		return listOfInactiveIndividuals;
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals(){
		//throw new UnsupportedOperationException("Not implemented.");
		List<Individual> listOfActiveIndividuals=new ArrayList<>();
		individualList=getListOfIndividuals();
		for(Individual individual : individualList){
			if(individual.isActive()){
				listOfActiveIndividuals.add(individual);

			}
		}
		return listOfActiveIndividuals;
	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return 
	 */
	public List<Team> getListOfTeams(){
		//throw new UnsupportedOperationException("Not implemented.");
		List<Team> teamList=new ArrayList<>();
		List<Individual> individuals=getListOfIndividuals();
		List<Individual> teamMembersList=null;
		List teamMapList=yamlData.get("teams");
		List localData=teamMapList;
		
		for(Object object : localData){
						
			
			Map<String, Object> map =  (Map<String, Object>) object;
			List members = (List) map.get("members");
			System.out.println(" members : "+members);
			teamMembersList = new ArrayList<>();
			for (int itr = 0; itr < members.size(); itr++) {
				int id = Integer.parseInt(members.get(itr).toString());
				Iterator<Individual> individualItr = individuals.iterator();
				while (individualItr.hasNext()) {
					Individual individual = individualItr.next();
    					if (individual.getId() == id) {
						teamMembersList.add(individual);
					}
				}
				
			}//for
		
			Map<String, Object> localMap=new HashMap<>();
			localMap.put("members", teamMembersList);
			localMap.put("id", map.get("id"));
			localMap.put("name", map.get("name"));
			Team team=new Team(localMap);
			teamList.add(team);
		
		
			System.out.println(" yaml : "+yamlData);

		}
	
		
		System.out.println("printing team ");
		Iterator<Team> individualItr = teamList.iterator();
		while (individualItr.hasNext()) {
			Team team = individualItr.next();
			System.out.println(team);
		
		}
		return teamList;
		}
}
















































/*for(Map<String, Object> map : teamMapList){
System.out.println(" map : "+map);
List list=(List) map.get("members");
System.out.println(" list :  "+list);
 teamMembersList=new ArrayList<>();

for(int itr=0;itr<list.size();itr++)	{

	System.out.println(" ? "+ list.get(itr));	    		
	int id=Integer.parseInt(list.get(itr).toString());
	 Iterator<Individual> individualItr=individuals.iterator();
	  while(individualItr.hasNext()){
		  Individual individual=individualItr.next();
		  if(individual.getId()==id){
			  teamMembersList.add(individual);
		   }
	  }	

}
System.out.println(" teamMembersList : "+teamMembersList);
map.put("members", teamMembersList);
System.out.println(map);
team=new Team(map);
teamList.add(team);
}
 */

