#include "randomAccess.h"
#include<ctime>
#include<cstdlib>

randomAccess::randomAccess()
{
	srand(time(0));
}

//random unvisited position
int randomAccess::randomPosition(int unvisited)
{
	return (rand() % unvisited);
}
