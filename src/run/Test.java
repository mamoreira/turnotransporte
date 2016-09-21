package run;

import java.util.ArrayList;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random tapilla= new Random();
		 ArrayList <Integer> discos = new ArrayList <Integer>(25);
		 ArrayList<ArrayList<Integer>> semanaturno = new ArrayList<ArrayList<Integer>>(7);
		    for(int i=0;i<6;i++){
		    	semanaturno.add(new ArrayList<Integer>(25));
		    }

		 //Step 1
		 int j=0;
		 while(j<25){// 25 fichas
			 int fichasDisco;
			 do{
				 fichasDisco =tapilla.nextInt(25)+1;//sorteo
			 }while(discos.contains(fichasDisco));// pregunto si ya fue seleccionado
			 discos.add(fichasDisco);// agrego a lista de seleccionados
			 if(j<4){//primer Grupo
				 semanaturno.get(0).add(fichasDisco);
			 }else
				 if(j<8){
					 semanaturno.get(1).add(fichasDisco);
				 }else
					 if(j<12){
						 semanaturno.get(2).add(fichasDisco);
					 }else
						 if(j<16){
							 semanaturno.get(3).add(fichasDisco); 
						 }else
							 if(j<20){
								 semanaturno.get(4).add(fichasDisco);
							 }else
								 if(j<25){
									semanaturno.get(5).add(fichasDisco); 
								 }
			 j++;
		 }
		 
		 for (int x=0;x<4;x++){
		 System.out.println( semanaturno.get(0).get(x)+" \t "+semanaturno.get(1).get(x)+"\t"+semanaturno.get(2).get(x)+" \t "+semanaturno.get(3).get(x)+"\t"+semanaturno.get(4).get(x)+" \t "+semanaturno.get(5).get(x));
		 }
		 System.out.println("\t \t \t \t \t"+semanaturno.get(5).get(4));
		 
		 
		 //Step 2

		 
		 
		 
		 
		 
//		 for(Integer disc: discos){ 
//			 System.out.println(""+disc.toString());
//		 }
		 
		 
		 


	}

}
