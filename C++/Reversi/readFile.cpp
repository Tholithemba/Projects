#include "readFile.h"
#include "game.h"
#include<iostream>
#include<fstream>
#include<sstream>
#include<string>
#include<vector>

//constructor
readFile::readFile(){}

//read puzzle dimensions from the file
std::vector<int> readFile::read_file()
{
	std::ifstream size_file;
	size_file.open("board_size.txt");

	if(!size_file)
	{
		std::cerr << "fail to open board_size.txt file"<<std::endl;
		exit(1);
	}
	
	int rows ;
	std::string line;
	std::vector<int> board_sizes;
	while(std::getline(size_file, line))
	{  
		str_conversion(line, rows);//we only take row since is nxn matrix
		board_sizes.push_back(rows);//push board sizes int vector
	}
	
	size_file.close(); 
	return board_sizes;
}

//convert string from the file to integer value
void readFile::str_conversion(std::string line, int &rows)
{
	std::stringstream ss(line);
	std::string temp1;
	std::string temp2;
	std::getline(ss,temp1,',');
	std::istringstream(temp1)>>rows;
	//rows = stoi(temp1);
	//std::getline(ss,temp2,',');
	//std::istringstream(temp2)>>columns;
	//columns = stoi(temp2);
}
