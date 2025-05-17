// Noah Sibley, N01512207 //

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    int logFunction;
    void LogFood();
    void PrintByDay();
    void PrintMostCalories();
    void ResetLog();

    printf("Welcome to the calorie tracking tool!\n");

    while(logFunction != -1){

        printf("What function do you want to do?\n");
        printf("1 = Log Food\n");
        printf("2 = Print Calories by Day\n");
        printf("3 = Print Food with Most Calories\n");
        printf("4 = Reset Log\n");
        printf("-1 = Quit\n");

        scanf("%d", &logFunction);


        if(logFunction == 1){
            LogFood();
        }
        if(logFunction == 2){
            PrintByDay();
        }
        if(logFunction == 3){
            PrintMostCalories();
        }
        if(logFunction == 4){
            ResetLog();
        }
        if(logFunction == -1){
            printf("Goodbye!\n");
            break;
        }

    }
    return 0;
}

void LogFood(){
    char FoodType[100];
    int whichDay, Cals;

    printf("What food item did you eat?\n");
    scanf("%s", &FoodType);
    printf("What day did you eat it? (1 = Monday; 7 = Sunday)\n");
    scanf("%d", &whichDay);
    printf("How many calories did it have?\n");
    scanf("%d", &Cals);

    FILE *IF;

    IF = fopen("calories.txt", "a");

    fprintf(IF, "%s %d %d\n", FoodType, whichDay, Cals);
    fclose(IF);
}

void PrintByDay(){
    int calCount[7] = {0};

    FILE *fp;

    fp = fopen("calories.txt", "r");

    char foodType[100];
    int weekDay, cals;

    while(fscanf(fp, "%s %d %d", foodType, &weekDay, &cals) != EOF){
        calCount[weekDay - 1] += cals;
    }

    printf("Monday: %d calories\n", calCount[0]);
    printf("Tuesday: %d calories\n", calCount[1]);
    printf("Wednesday: %d calories\n", calCount[2]);
    printf("Thursday: %d calories\n", calCount[3]);
    printf("Friday: %d calories\n", calCount[4]);
    printf("Saturday: %d calories\n", calCount[5]);
    printf("Sunday: %d calories\n", calCount[6]);

}


void PrintMostCalories(){
    
    FILE *fp;

    fp = fopen("calories.txt", "r");

    char foodType[100];
    char maxFood[100];
    int weekDay, cals;
    int maxCals = 0;

    while(fscanf(fp, "%s %d %d", foodType, &weekDay, &cals) != EOF)
    {

        if(cals > maxCals)
        {
            maxCals = cals;
            strcpy(maxFood, foodType);

        }

    }

    fclose(fp);

    printf("The food with the most calories is %s with %d calories\n", maxFood, maxCals);


}


void ResetLog(){
    FILE *fp;

    fp = fopen("calories.txt", "w");

    printf("File 'calories.txt' has been reset.\n");
}
