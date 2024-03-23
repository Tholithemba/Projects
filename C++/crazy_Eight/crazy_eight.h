#ifndef CRAZY_EIGHT_H
#define CRAZY_EIGHT_H

#include<vector>

class crazy_eight{
	
 public:
 		crazy_eight();
 		typedef std::vector<std::pair<std::string, std::string>> card;
 
 		std::string card_type[4] = {"club","diamond", "heart","spade"};
 		std::string card_letters[3] = {"king", "queen", "jack"};
		card deck;
		card pile;
		void start();
		void updateStackSize(int pos);
		void openFirstCard();
		void matchPileCard(card &player_turn, std::string curr_player);
		void initializingStack();
		std::string eight = "8";
		
		void dish_cards();
		std::string four_players[4] = {"payer_1", "payer_2", "payer_3", "payer_4"};
		card first_player_stack;
		card second_player_stack;
		card third_player_stack;
		card fourth_player_stack;
		void updatePlayerStack(card &player_stack, int pos);
		
 private:
 		int each_player_stack = 5;
		int card_pos;
		int stack_size;
		bool game_over = false;
		void gameOver();
		void copyBackToDeck();
		int points(card &player_cards, int size);
		int count = 0;
		int three_added = 3;
};
#include "crazy_eight.cpp"
#endif
