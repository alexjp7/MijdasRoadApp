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

const int totalName=200,totalAddress=300,neededServices=100,accidentTypes=10,motoristNumber=150;

int main(int argc, char** argv) {
	string username,temp,names[totalName],addresses[totalAddress],accidents[accidentTypes],motorists[motoristNumber];
	srand (time(NULL));
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
	
	int randMot,randAddress,randAccident;
	
	fout.open("motorists.sql");
	for(int i=0;i<motoristNumber;i++){
		
		fout<<"INSERT INTO MOTORIST(username,hasMembership,license) VALUES (\""
		<<names[i]
		<<"\",false,"
		<<rand()%99999999
		<<");\n";
		motorists[i]=names[i];
	}
	
	fout.close();
	
	
	fout.open("inserts.sql");
	for(int i=1;i<=neededServices;i++){
		randMot = rand()%motoristNumber;
		randAddress=i;
		randAccident=rand()%accidentTypes;
		
		fout<<"INSERT INTO SERVICE_REQUEST VALUES(" 
        << i 
        <<" , \"" 
        << motorists[randMot] 
        << "\" , \"" 
        << addresses[randAddress] 
        << "\" , \""
        <<accidents[randAccident] 
        << "\" , false); \n";
	}
	fout.close();
	return 0;
	
	
	
	
}

