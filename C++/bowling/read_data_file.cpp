#include "read_data_file.h"
#include "testcase.h"
#include <iostream>


//constructor
read_data_file::read_data_file()
{
	input_file();
}


//read actal data from the file
void read_data_file::input_file()
{
	testcase tc;
	std::ifstream input;
	int size = 10;
	spare_strike = new std::string[size];// initialise actaal spare strike frame
	std::string line;
	
	if(tc.caseNumber() == 1)
		input.open("./arrayfiles/arr1.txt"); //read first file
	else if(tc.caseNumber() == 2)
		input.open("./arrayfiles/arr2.txt");//read second file
	else
		input.open("./arrayfiles/arr3.txt");//read third file
	
	if(!input)
	{
		std::cerr<<"file failed to open"<<std::endl;
		exit(1);
	}
	int count = 0;
	while(getline(input, line))
	{	//write actual data on spare strike frame
		spare_strike[count] = line;
		count++;
	}
	input.close();
}
