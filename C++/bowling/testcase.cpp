#include "testcase.h"
#include "read_data_file.h"

int case_number = 1;//case number to be checked

//check if spare strike frame from the user match the actaul spare strike frame
std::string testcase::sp_strArraytest(std::string* &str_sp)
{
	int size = 10;
	std::string* test_sp_str;
	test_sp_str = new std::string[size];
	
	orig_array(test_sp_str);//actual spare strike frame

	for(int r = 0; r < size; r++)
	 if(str_sp[r] != test_sp_str[r] )
	 	return "case 2 failed";
	 	
	return "case 2 passed";
}
//return case passed if they match otherwise return false


//for each array check if axpected running totals
//correspond to actual running totals
std::string testcase::runing_totalstest(int sum)
{
	switch(caseNumber())
	{
		case 1: if(sum == 168) return "case 1 passed";
						else return "case 1 failed";
		break;
		
		case 2: if(sum == 187) return "case 1 passed";
						else return "case 1 failed";
		break;
		
		case 3: if(sum == 149) return "case 1 passed";
						else return "case 1 failed";
		break;
		
		default : "wrong value";
	}
}
//return case passed if they match otherwise return case failed

//get actual array from the file
void testcase::orig_array(std::string* &test_sp_str)
{
	int size = 10;
	int r = 0;
	read_data_file rf;
	
	for(r = 0; r < size; r++)
		test_sp_str[r] = rf.spare_strike[r];
	
	incrementCaseNumber();//increment case number
}


//case number. all cases must pass for the program 
//to be considered as  successful
int testcase::caseNumber()
{
	return case_number;
}

//move to next case number
void testcase::incrementCaseNumber()
{
	case_number++;
}

