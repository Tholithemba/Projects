#include "deck_cards.h"
#include<bits/stdc++.h>

crazy_eight::crazy_eight(){
	
	initializingStack();
	openFirstCard();
	start();
	gameOver();
}


void crazy_eight::initializingStack(){

	//creating deck of cards
	for(int card_num = 1; card_num <= 10; card_num++)
		for(int type = 0; type < 4; type++)
			deck.push_back(std::make_pair(std::to_string(card_num), card_type[type]));
			
	for(int card_letter = 0; card_letter <3; card_letter++)
		for(int type = 0; type < 4; type++)
			deck.push_back(std::make_pair(card_letters[card_letter], card_type[type]));	
		
	dish_cards();
}


//initializing stack players
void crazy_eight::dish_cards(){
	deck_cards d;
	int pos;
	
	for(int i = 0; i < each_player_stack; i++){
		pos = d.card_position(deck.size());
		first_player_stack.push_back(deck[pos]);
		updateStackSize(pos);
		
		pos = d.card_position(deck.size());
		second_player_stack.push_back(deck[pos]);
		updateStackSize(pos);
		
		pos = d.card_position(deck.size());
		third_player_stack.push_back(deck[pos]);
		updateStackSize(pos);
		
		pos = d.card_position(deck.size());
		fourth_player_stack.push_back(deck[pos]);
		updateStackSize(pos);
	}
}
//making sure that eight be not the first card to sit on top of the pile when first opened
void crazy_eight::openFirstCard(){
	deck_cards d;
	
	int pos = d.card_position(deck.size());
	
	while(deck[pos].first == eight){
		pos	= d.card_position(deck.size());
	}
	
	pile.push_back(deck[pos]);
	updateStackSize(pos);
}

//game begins, each player is given a turn to play
void crazy_eight::start(){

	std::string curr_player;
	while(game_over == false){
		
		curr_player = "FIRST";  //first player turn
		matchPileCard(first_player_stack, curr_player);
		
		curr_player = "SECOND"; //second player turn
		if(game_over == false)
			matchPileCard(second_player_stack, curr_player);
		
		curr_player = "THIRD"; //third player turn
		if(game_over == false)
			matchPileCard(third_player_stack, curr_player);
		
		curr_player = "FOURTH"; //fourth player turn
		if(game_over == false)
			matchPileCard(fourth_player_stack, curr_player);
		
	}
}

//if player card matched the top pile card, add player card on top of the pile else next player turn
void crazy_eight::matchPileCard(card &player_turn, std::string curr_player){
	
	int pos = 0;
	bool matched = false;
	deck_cards d;
	int player_stack_size = player_turn.size();
	std::cout<<"::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"<<"\n";
	std::cout<<curr_player<<" IN:"<<"\n";
	std::cout<<curr_player<<" size: "<<player_stack_size<<"\n";
	for(int i = 0; i < player_stack_size; i++)
		std::cout<<player_turn[i].first<<" "<<player_turn[i].second<<"\n";
	
	std::cout<<"--------------------------------------------------------------------------------------"<<"\n";
	std::cout<<pile[pile.size()-1].first<<" "<<pile[pile.size()-1].second<<" is on top of the pile"<<"\n";
	std::cout<<"--------------------------------------------------------------------------------------"<<"\n";
	while(pos < player_stack_size and	matched == false){
		if(player_turn[pos].first == pile[pile.size()-1].first or
		   player_turn[pos].second == pile[pile.size()-1].second
		   or player_turn[pos].first == eight){
		   
		   pile.push_back(player_turn[pos]);
		   updatePlayerStack(player_turn, pos);
		   matched = true;
		}
		pos++;
	
	}
	std::cout<<"MATCHED: "<<matched<<"\n";
	if(matched == false){
		if(deck.size() > three_added)
			copyBackToDeck();
			
		for(int i = 0; i < three_added; i++){
			pos = d.card_position(deck.size());
			player_turn.push_back(deck[pos]);
			updateStackSize(pos);
		}
	}
	std::cout<<curr_player<<" OUT:"<<"\n";
	std::cout<<"::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"<<"\n";
	if(player_turn.size() == 0){
		std::cout<<"GAME OVER "+curr_player + " PLAYER WON !!!!!"<<"\n";
		game_over = true;
	}
	else if(deck.size() == 0){
		copyBackToDeck();
	}
}

//copying cards from pile to deck
void crazy_eight::copyBackToDeck(){
		int pile_size = pile.size();
		for(int pos = 0; pos < pile_size; pos++)
			deck.push_back(pile[pos]);
			
		pile.clear();
		openFirstCard();
}

//removing played card from the stack
void crazy_eight::updateStackSize(int pos){
	deck.erase(deck.begin()+pos);
}

//removing played card from the stack players
void crazy_eight::updatePlayerStack(card &player_stack, int pos){
		player_stack.erase(player_stack.begin() + pos);
}

//game is finished
void crazy_eight::gameOver(){
	
	int player_points = 0;
	std::cout<<"========================================================================\n";
	std::cout<<"first player cards \n";
	player_points = points(first_player_stack, first_player_stack.size());
	std::cout<<"PLAYER ONE POINTS: "<<player_points<<std::endl;
	std::cout<<"========================================================================\n";
	
	std::cout<<"Second player cards \n";
	player_points = points(second_player_stack, second_player_stack.size());
	std::cout<<"PLAYER TWO POINTS: "<<player_points<<std::endl;
	std::cout<<"========================================================================\n";
	
	std::cout<<"Third player cards \n";
	player_points = points(third_player_stack, third_player_stack.size());
	std::cout<<"PLAYER THREE POINTS: "<<player_points<<std::endl;
	std::cout<<"========================================================================\n";
	
	std::cout<<"Fourth player cards \n";
	player_points = points(fourth_player_stack, fourth_player_stack.size());
	std::cout<<"PLAYER FOUR POINTS: "<<player_points<<std::endl;
	std::cout<<"========================================================================\n";
	
}

//calculating points for each player when the game is over
int crazy_eight::points(card &player_cards, int size){
	int total_points = 0;
	std::cout<<"size of player stack: "<<size<<"\n";
	for(int i = 0;  i < size; i++)
		std::cout<<player_cards[i].first<<" "<<player_cards[i].second<<"\n";
		
	for(int pos = 0; pos < size; pos++){
		if(player_cards[pos].first == "8")
			total_points = total_points + 50;
		else if(player_cards[pos].first == "king" or player_cards[pos].first == "queen"
				or player_cards[pos].first == "jack" or player_cards[pos].first == "10")
			total_points = total_points + 10;
		else if(player_cards[pos].first == "1")
			total_points = total_points + 1;
	}

	return total_points;
}





