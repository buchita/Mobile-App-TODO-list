/*
 *   Author: Buchita Gitchamnan
 *   Student Number: C16348651
 *   Project: Simple Food Receipt
 *   Reference: Youtube channel called "Coding in Flow", Stackoverflow.com
 *   Purpose: Create Database for this prject, check if there is an existing db then return that db else create a new one.
 *      populating the data in the db
 *
 */



package com.example.assignmentmobdev;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

//declare the db name
@Database(entities = {Food.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase
{
    //attributes
    private static FoodDatabase instance;
    public abstract FoodDao foodDao();


    //synchronised avoid multiple threads accessing this
    public static synchronized FoodDatabase getInstance(Context context)
    {
        //if there is no db
        if (instance==null)
        {
            //create the db
            instance = Room.databaseBuilder(context.getApplicationContext().getApplicationContext(),
                    FoodDatabase.class, "food_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();



        }
        //else just use the existing one.
        return instance;
    }//end sync


    //populating the db with assigned data
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedDbAsyncTask(instance).execute();
        }
    };

    private static class PopulatedDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private FoodDao mDao;

        public PopulatedDbAsyncTask(FoodDatabase db) {
            mDao =db.foodDao();
        }

        //assign the data to db
        @Override
        protected Void doInBackground(Void... voids) {
            //samples
            mDao.insert(new Food("Garlic Bread","½ cup unsalted butter\n" +
                    "¼ cup extra-virgin olive oil\n" +
                    "2 cloves garlic cloves, peeled, crushed\n" +
                    "Parsley leaves, finely chopped\n" +
                    "1 baguette\n" +
                    "Kosher salt\n" +
                    "Finely grated Parmesan","1. Preheat oven to 450°. Heat butter and oil in a small saucepan over medium heat. Add garlic and parsley and cook, stirring occasionally, until butter is completely melted.\n" +
                    "\n" +
                    "2. Slice bread in half lengthwise, then place on a baking sheet and brush cut sides with butter mixture.\n" +
                    "\n" +
                    "3. Slice crosswise 1\" thick, without cutting all the way through; season with salt and top with Parmesan. Bake until golden, 10–15 minutes."));
            mDao.insert(new Food("Chicken Noodle Soup","900ml chicken or vegetable stock (or Miso soup mix)\n" +
                    "1 boneless, skinless chicken breast, about 175g/6oz\n" +
                    "1 tsp chopped fresh root ginger\n" +
                    "1 garlic clove, finely chopped\n" +
                    "50g rice or wheat noodles\n" +
                    "2 tbsp sweetcorn, canned or frozen\n" +
                    "2-3 mushrooms\n" +
                    "2 spring onions\n" +
                    "2 tsp soy sauce","1. Pour 900ml chicken or vegetable stock into a pan and add  boneless, skinless chicken breast, 1 tsp chopped root ginger and 1 finely chopped garlic clove.\n" +
                    "\n" +
                    "2. Bring to the boil, then reduce the heat, partly cover and simmer for 20 mins, until the chicken is tender.\n" +
                    "\n" +
                    "3. Remove the chicken to a board and shred into bite-size pieces using a couple of forks.\n" +
                    "\n" +
                    "4. Return the chicken to the stock with 50g rice or wheat noodles, 2 tbsp sweetcorn, 2-3  thinly sliced mushrooms, 1 shredded spring onion and 2 tsp soy sauce.\n" +
                    "\n" +
                    "5. Simmer for 3-4 mins until the noodles are tender\n" +
                    "\n" +
                    "6. Ladle into two bowls and scatter over the remaining shredded spring onion, mint or basil leaves and shredded chilli if using. Serve with extra soy sauce for sprinkling."));
            mDao.insert(new Food("Upgraded Ramen ","2 cups water\n" +
                    "\n" +
                    "1 package instant ramen\n" +
                    "\n" +
                    "3 slices of cooked bacon, chopped\n" +
                    "\n" +
                    "1 egg, fried\n" +
                    "\n" +
                    "1 tablespoon thinly sliced green onions\n" +
                    "\n" +
                    "Kosher salt, to taste\n" +
                    "\n" +
                    "Ground black pepper, to taste","1. Top your prepared ramen with bacon and fried egg. Garnish with green onions and season with salt and pepper."));
            mDao.insert(new Food("Chow Mein with Chicken and Vegetables","25g mushrooms\n" +
                    "chichen stock\n" +
                    "340g egg noodles\n" +
                    "2 tbsp sunflower oil\n" +
                    "1 garlic clove\n" +
                    "200g chicken \n" +
                    "2 green peppers\n" +
                    "2 tbsp soy sauce\n" +
                    "100g bean sprouts\n" +
                    "corianders\n" +
                    "1tsp sesame oil\n","1. Place the mushrooms in a small bowl and pour in enough boiling water to cover them. Leave to soak for 10 minutes. Line a sieve with muslin or kitchen paper and place it over a bowl, then pour the mushrooms and their soaking liquid into it. Measure the strained liquid and make it up to 100 ml (3 ½ fl oz) with chicken or vegetable stock if necessary, then set aside. Discard any tough stalks from the mushrooms, slice them and set aside.\n" +
                    "\n" +
                    "2. While the mushrooms are soaking, place the noodles in a large mixing bowl and pour in enough boiling water to cover them generously. Leave to soak for 4 minutes, or according to the packet instructions, until tender. Drain well and set aside.\n" +
                    "\n" +
                    "3. Heat a wok or large frying pan over a high heat. Add 1 tbsp of the sunflower oil and, when it is hot, stir in the garlic, ginger, chilli and five-spice powder. Stir-fry for 30 seconds, taking care not to let the flavourings burn.\n" +
                    "\n" +
                    "4. Add the strips of pork and continue stir-frying for about 2 minutes or until they are cooked through. Use a draining spoon to remove the pork from the wok and set it aside.\n" +
                    "\n" +
                    "5. Add the remaining oil to the wok and heat until it is almost smoking. Stir in the peppers, broccoli, celery and mushrooms, and stir-fry for 2 minutes. Stir in the mushroom liquid, soy sauce and rice wine or sherry, then return the pork to the wok. \n" +
                    "\n" +
                    "6. Continue cooking, stirring constantly, for about 1 minute or until the pork is reheated.\n" +
                    "\n" +
                    "7. Stir in the noodles, then the bean sprouts and toss together briefly, just long enough to heat the ingredients without softening the bean sprouts, as they should retain their crunch.\n" +
                    "\n" +
                    "8. Stir in the chopped coriander and sprinkle with the sesame oil. Serve the chow mein immediately, garnished with coriander leaves."));
            mDao.insert(new Food("Macaroni Cheese","50g butter\n" +
                    "1 small onion, chopped\n" +
                    "1 1/2 tablespoons plain flour\n" +
                    "1 teaspoon salt\n" +
                    "750ml (1¼ pints) milk\n" +
                    "225g uncooked macaroni\n" +
                    "175g grated mature Cheddar cheese","1. Melt butter in a saucepan pan over medium heat. Add onion and cook until soft. Stir in flour and salt.\n" +
                    "\n" +
                    "2. Add milk and macaroni to saucepan and bring to the boil. Reduce heat and cover. Simmer for 15 minutes or until pasta is tender, stirring occasionally.\n" +
                    "\n" +
                    "3. Add cheese and stir until cheese melts. Serve."));
            mDao.insert(new Food("Chicken Curry","2 teaspoons olive oil\n" +
                    "450g chicken breast fillets, cut into strips\n" +
                    "1 tablespoon Thai red curry paste\n" +
                    "1 small courgette, halved lengthways and sliced\n" +
                    "1 red pepper, sliced into strips\n" +
                    "1 medium carrot, sliced\n" +
                    "1 red onion, quartered then sliced\n" +
                    "400g tin light coconut milk\n" +
                    "1 tablespoon cornflour\n" +
                    "2 tablespoons chopped fresh coriander","1. Heat the oil in a large frying pan or wok over medium-high heat. Add the chicken pieces; cook and stir for about 3 minutes. Mix in the curry paste, courgette, pepper, carrot and onion. Cook and stir for a few minutes.\n" +
                    "\n" +
                    "2. Whisk together the coconut milk and cornflour to dissolve, then add to chicken mixture. Bring to the boil, then reduce to a simmer over medium heat for 1 minute, or until thickened. Right before serving, stir in the coriander."));
            mDao.insert(new Food("Spaghetti Bolognese","2 tbsp olive oil\n" +
                    "500g lean minced beef\n" +
                    "1 large onion, finely chopped\n" +
                    "1 carrot, peeled and finely chopped\n" +
                    "2 celery sticks, finely chopped\n" +
                    "4 garlic cloves, crushed\n" +
                    "400g can chopped tomatoes\n" +
                    "450g jar or carton tomato passata\n" +
                    "good pinch of dried oregano, or to taste\n" +
                    "400g spaghetti\n" +
                    "grated Parmesan cheese, to serve","1. Heat a wok or large, deep frying pan with the olive oil. Crumble the beef into the pan and stir-fry over a high heat for 2 minutes to break up the meat and brown it.\n" +
                    "\n" +
                    "2. Add the onion, carrot, celery and garlic. Stir over a medium heat for 1 minute, then add the chopped tomatoes and the passata. Stir well, then add the oregano and seasoning. Cook over a medium heat so that the sauce boils gently, stirring occasionally, for 15 minutes or until thick.\n" +
                    "\n" +
                    "3. While the sauce is cooking, cook the spaghetti in a pan of salted boiling water for 10 minutes, or according to the pack instructions, until tender with a bite at the centre. \n" +
                    "\n" +
                    "4. Drain and turn into a warmed serving bowl.\n" +
                    "\n" +
                    "5. Taste the sauce and adjust the seasoning, then pour over the hot pasta. Toss gently and serve immediately with grated Parmesan cheese."));
            mDao.insert(new Food("Carbonara","3 rashers of bacon\n" +
                    "1 red onion\n" +
                    "1/2 tub of light single cream (or double cream)\n" +
                    "1/4 block of Parmasan cheese\n" +
                    "2 egg yolks\n" +
                    "sprinkle of chives\n" +
                    "Pasta twists of spaghetti, enough for two people","1. Fry the onion and bacon for 10 mins. In a a seperate pan boil the pasta or spaghetti, when the pasta is cooked drain and add to a mixing bowl, seperate the egg yolks from the egg whites and add the yolks to the cooked pasta, along with the cheese and cream, and the bacon and onion from the frying pan. \n" +
                    "\n" +
                    "2. Sprinkle with the chives and serve, add salt and pepper to taste."));
            mDao.insert(new Food("Sweet and Spicy Tofu with Vegetables","3 tablespoons groundnut oil\n" +
                    "900g (1 lb) firm tofu, cubed\n" +
                    "1 red onion, sliced\n" +
                    "1 red pepper, sliced\n" +
                    "1 green chilli, chopped\n" +
                    "3 cloves garlic, crushed\n" +
                    "5 tablespoons hot water\n" +
                    "3 tablespoons white wine vinegar\n" +
                    "3 tablespoons soy sauce\n" +
                    "1 tablespoon dark brown soft sugar\n" +
                    "1 teaspoon cornflour\n" +
                    "1 teaspoon dried crushed chillies (optional)","1. Heat oil in a wok or large frying pan over medium high heat. Toss the tofu into the oil, and cook until browned on all sides. Once browned, toss in onion, red pepper, chilli and garlic; cook until just tender, about 5 minutes.\n" +
                    "\n" +
                    "2. In a small bowl, whisk together the hot water, vinegar, soy sauce, brown sugar, cornflour and crushed chillies. \n" +
                    "\n" +
                    "3. Pour over tofu and vegetables, toss to coat, and simmer 3 to 5 minutes, or until sauce thickens slightly."));
            mDao.insert(new Food("Fish and Chips","125g plain flour, plus extra for dusting\n" +
                    "300ml beer\n" +
                    "4 potatoes, peeled and cut into chips or wedges\n" +
                    "2 skinless, boneless fish fillets, cut into large pieces\n" +
                    "vegetable oil for frying\n" +
                    "1 lemon\n" +
                    "Tartare sauce\n" +
                    "8 tablespoons mayonnaise\n" +
                    "2 tablespoons soured cream\n" +
                    "2 gherkins, finely chopped\n" +
                    "1 tablespoons capers, rinsed and finely chopped\n" +
                    "1 tablespoons finely chopped fresh dill\n" +
                    "salt and pepper to taste","To make the tartare sauce:\n" +
                    "In a small bowl combine all ingredients. Stir to combine, then season to taste.\n" +
                    "\n" +
                    "To make the batter: \n" +
                    "1. Sift flour in large bowl. Make a well in centre of flour. Gradually whisk in beer until batter is smooth or use a hand blender if preferred. Set aside.\n" +
                    "\n" +
                    "2.Heat oil in a large pan. Dust each piece of fish and potato in plain flour and dip into batter mixture and deep fry in small batches for 5-10 minutes.\n" +
                    "\n" +
                    "3. Remove and drain on kitchen paper. Serve with lemon wedges and tartare sauce."));
            mDao.insert(new Food("pork","pork","chopped the pork"));

            return null;
        }
    }

}
