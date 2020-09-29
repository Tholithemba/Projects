#ifndef READ_DATA_FILE_H
#define READ_DATA_FILE_H
#include<string>
#include <fstream>


//definitions
class read_data_file
{
	public:
		read_data_file();
		std::string* spare_strike;
		
	private:
		void input_file();
};

#include "read_data_file.cpp"
#endif
