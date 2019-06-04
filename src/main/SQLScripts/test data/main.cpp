#include <iostream>
#include <fstream>
#include <cctype>
#include <algorithm>
#include <string>
#include <stdio.h>      /* printf, scanf, puts, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
/* run this program using the console pauser or add your own getch, system("pause") or input loop */

using namespace std;

const int totalName=300,totalAddress=300,neededServices=1000,accidentTypes=10,motoristNumber=150,neededRego=150,mechanicNumber=150;

int main(int argc, char** argv) {
	string username,temp,names[totalName],addresses[totalAddress],accidents[accidentTypes],motorists[motoristNumber];
	srand (time(NULL));
	int lNums[motoristNumber];
	ifstream fin;
	fin.open("userBase.txt");
	for(int i=0;i<totalName;i++){
		fin>>username>>temp>>temp>>temp>>temp;
		username.erase(remove_if(username.begin(), username.end(),[]( auto const& c ) -> bool { return !std::isalnum(c); } ), username.end());//cleans users
		
		names[i]=username;
	}
	fin.close();
	fin.open("addressDataMock.txt");
	for(int i=0;i<totalAddress;i++){
		getline(fin,username);
	//	cout<<username<<endl;
		addresses[i]=username;
	}
	fin.close();
	fin.open("accidents.txt");
	for(int i=0;i<accidentTypes;i++){
		getline(fin,username);
	//	cout<<username<<endl;
		accidents[i]=username;
	}
	fin.close();
	ofstream fout;
	
	int randMot,randAddress,randAccident,randCar;
	
	fout.open("motorists.sql");
	for(int i=0;i<motoristNumber;i++){
		int lNum=rand()%99999999;
		fout<<"INSERT INTO MOTORIST(username,hasMembership,lnum) VALUES (\""
		<<names[i]
		<<"\",false,"
		<<lNum
		<<");\n";
		motorists[i]=names[i];
		lNums[i]=lNum;
	}
	
	fout.close();
	
	
//	fout.open("inserts.sql");
//	for(int i=1;i<=neededServices;i++){
//		randMot = rand()%motoristNumber;
//		randAddress=rand()%totalAddress;
//		randAccident=rand()%accidentTypes;
//		
//		fout<<"INSERT INTO SERVICE_REQUEST VALUES(" 
//        << i 
//        <<" , \"" 
//        << motorists[randMot] 
//        << "\" , \"" 
//        << addresses[randAddress] 
//        << "\" , \""
//        <<accidents[randAccident] 
//        << "\" , false,NULL,false); \n"
//		;
//	}
//	fout.close();
	
	fout.open("vehicle.sql");
	string cars[] = {"Subaru","Volkswagon","Toyota","Porsche","Ford","Nissan","Suzuki","Mitsubishi","Koenigsegg","Tesla","BMW","Audi","Bentley","Chrysler","Dodge","Mazda","Mercedes","Ferrari","Lambourgini","Chevy","Fiat","Peugot","Jeep","Hyundai","Honda"};
	string colors[] = {"black", "red", "white", "silver", "yellow", "orange"};
	string models[]={"Sedan","4x4","hatchback","minibus"};
	char x[26] ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	for(int i=1;i<=neededRego;i++){
		randCar=rand()%25;
		
		fout<<"INSERT INTO VEHICLE VALUES(" 
        << lNums[i]
        <<" , \"" 
        <<x[rand()%26]
        <<x[rand()%26]
        <<x[rand()%26]
        << rand()%999
        << "\" , \"" 
        << cars[randCar] 
        << "\" , \""
        <<models[rand()%4] 
        << "\" , \""
        <<colors[rand()%6]
        << "\" , false); \n"
		;
	}
	fout.close();
	
	fout.open("mechanics.sql");
	for(int i=0;i<mechanicNumber;i++){
		fout<<"INSERT INTO MECHANIC VALUES (\""
		<<names[150+i]
		<<"\",0,"
		<<rand()%99999999
		<<");\n";
	}
	fout.close();
	
	return 0;
	
	
	
	
}

