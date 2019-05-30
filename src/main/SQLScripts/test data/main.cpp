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

int totalName=200,totalAddress=300,neededServices=100,accidentTypes=10;

int main(int argc, char** argv) {
	string username,temp,names[totalName],addresses[totalAddress],accidents[accidentTypes];
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
	
	int randUser,randAddress,randAccident;
	
	fout.open("inserts.sql");
	for(int i=1;i<=neededServices;i++){
		randUser = rand()%totalName;
		randAddress=rand()%totalAddress;
		randAccident=rand()%accidentTypes;
		
		fout<<"INSERT INTO SERVICE_REQUEST VALUES(\""<<i<<"\",\""<<names[randUser]<<"\",\""<<addresses[randAddress]<<"\",\""<<accidents[randAccident]<<"\",false)\n";
	}
	return 0;
	
	
	
	
}

