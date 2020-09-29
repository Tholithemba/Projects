#include "randomSpace.h"
#include<ctime>
#include<cstdlib>

//constructor
randomSpace::randomSpace()
{
	srand(time(0));
}

//get random positon from valid empty spaces available
int randomSpace::getRandPosition(int bound)
{
	return (rand()% bound);
}
