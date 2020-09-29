#ifndef TESTCASE_H
#define TESTCASE_H
#include <string>

class testcase
{
	public:
		std::string sp_strArraytest(std::string* &str_sp);
		std::string runing_totalstest(int sum);
		int caseNumber();
		
	private:
		void orig_array(std::string* &arr);
		void incrementCaseNumber();
};

#include "testcase.cpp"
#endif
