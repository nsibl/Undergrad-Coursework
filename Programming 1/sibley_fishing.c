#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int baitType;
int fishType;
int remainingTime = 720;
int nothingTime = 5;
int flounderTime = 10;
int snapperTime = 20;
int sharkTime = 60;
int nothingPrice = 0;
int flounderPrice = 100;
int snapperPrice = 250;
int sharkPrice = 1000;
int walletSize = 0;

int goFish(){
    srand((int)time(0));
    if(baitType == 1){
        fishType = rand() % 100 + 1;
        if(fishType>0 && fishType<=60){
            remainingTime = remainingTime  - 5;

            printf("You caught nothing!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }

        if(fishType>60 && fishType <= 85){
            remainingTime = remainingTime - 10;
            walletSize = walletSize + 100;

            printf("You caught a flounder!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }
        if(fishType>85 && fishType <= 99 ){
            remainingTime = remainingTime - 20;
            walletSize = walletSize + 250;

            printf("You caught a red snapper!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }
        if(fishType>99 && fishType <= 100){
            remainingTime = remainingTime - 60;
            walletSize = walletSize + 1000;

            printf("You caught a shark!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }
    }
    if(baitType == 2){
        fishType = rand() % 100 + 1;
        if(fishType>0 && fishType <= 70){
            remainingTime = remainingTime - 5;

            printf("You caught nothing!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }
    
        if(fishType>70 && fishType <= 90){
            remainingTime = remainingTime - 20;
            walletSize = walletSize + 250;

            printf("You caught a red snapper!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }

        if(fishType>90 && fishType <= 95){
            remainingTime = remainingTime - 10;
            walletSize = walletSize + 100;

            printf("You caught a flounder!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }

        if(fishType>95 && fishType <= 100){
            remainingTime = remainingTime - 60;
            walletSize = walletSize + 1000;

            printf("You caught a shark!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }
        
    }

    if(baitType == 3){
        fishType = rand() % 100 + 1;
        if(fishType>0 && fishType <= 80){
            remainingTime = remainingTime - 5;

            printf("You caught nothing!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }

        else if(fishType>80 && fishType <= 95){
            remainingTime = remainingTime - 60;
            walletSize = walletSize + 1000;

            printf("You caught a shark!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }

        else if(fishType>95 && fishType <= 97.5){
            remainingTime = remainingTime - 10;
            walletSize = walletSize + 100;

            printf("You caught a flounder!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }

        else if(fishType>97.5 && fishType <= 100){
            remainingTime = remainingTime - 20;
            walletSize = walletSize + 250;

            printf("You caught a red snapper!\n");
            printf("Remaining time: %d\n", remainingTime);
            printf("Current wallet size: $%d\n", walletSize);
        }
    }



}


int main(void){

    printf("Welcome to the fishing simulator!\n");
    do{
        printf("What type of bait will you use?\n");
        printf("1 = Worms\n");
        printf("2 = Jig\n");
        printf("3 = Chum\n");
        printf("-1 = Leave Early\n");
        scanf("%d", &baitType);

        goFish();

    }
    while(remainingTime > 0 && baitType != -1);
    
    printf("Thank you for fishing.\n");
    printf("Final wallet size: $%d", walletSize);
    

    return 0;

}

