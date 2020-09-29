#ifndef READFILE_H
#define READFILE_H
#include<string>
class readFile
{
	public:
		readFile();
		std::vector<int> read_file();
		
	private:
		void str_conversion(std::string line, int &rows);
};

#include "readFile.cpp"
#endif
