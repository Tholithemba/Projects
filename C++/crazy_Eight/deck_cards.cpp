#include<ctime>
#include<cstdlib>

deck_cards::deck_cards(){
	srand(time(0));
}

int deck_cards::card_position(int stack_size){
	return (rand() % stack_size);
}
