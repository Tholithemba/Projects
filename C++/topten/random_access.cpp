#include "random_access.h"
#include<ctime>
#include<cstdlib>

//constructor
random_access::random_access()
{
	srand(time(0));
}


//return random mumber from 1 up to number of cards left
int random_access::random_position(int number_of_cards)
{
	return (rand() % number_of_cards);
}
