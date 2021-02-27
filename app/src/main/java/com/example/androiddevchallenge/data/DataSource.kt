package com.example.androiddevchallenge.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DataSource {

    val puppies = listOf(
        Puppy(
            id = "1",
            name = "Benji",
            age = "2 months",
            breed = "German Shepherd",
            imageUrl = "https://www.thesprucepets.com/thmb/Eh-n-bxfKQTopLQZ9gTiOChF-jY=/1080x810/smart/filters:no_upscale()/16_Love-5bb4c12bc9e77c00263933b3.jpg",
            about = Puppy.About(
                short = "My name is Benji and I am a five and a half year old German Shepherd whose owner has made the hard decision to find me a new home.",
                long = """
                        My name is Benji and I am a five and a half year old German Shepherd whose owner has made the hard decision to find me a new home.

                        I am a gentle dog who wants a family who will allow me to be with them when they are home. True to the nature of the German Shepherd breed I am extremely loyal and protective of my human pack.

                        I have a lot of energy so I am looking for a home with an energetic person or couple, who have recently had a German Shepherd and understand the time, commitment and on going training I will requirement.
                    """.trimIndent()
            ),
            fee = 450,
            health = Puppy.Health(
                deSexed = true,
                vaccinated = false,
                wormed = true
            ),
            location = Puppy.Location(
                lat = -37.79488771043525,
                lng = 144.98232219111486
            )
        ),
        Puppy(
            id = "2",
            name = "Daisy",
            age = "5 months",
            breed = "Fox Terrier Mix",
            imageUrl = "https://res.cloudinary.com/petrescue/image/upload/h_638,w_638,c_pad,q_auto:best,f_auto/v1614416619/hpyrcanczny2cetxzjec.jpg",
            about = Puppy.About(
                short = "Daisy is a funny little girl who makes friends where ever she goes.",
                long = """
                        Daisy is a funny little girl who makes friends where ever she goes. She absolutely adores people and is not shy about making this known.

                        Whether it be roughhousing or snuggling, Daisy, said to be a Fox Terrier Mix, takes pride in instigating all interactions. This convivial darling is a five year old tri-pod who doesn't let her relatively recent amputation get in the way of living life to the fullest.
                    """.trimIndent()
            ),
            fee = 200,
            health = Puppy.Health(
                deSexed = true,
                vaccinated = true,
                wormed = true
            ),
            location = Puppy.Location(
                lat = -37.792581650252345,
                lng = 144.92327067605885
            )
        ),
        Puppy(
            id = "3",
            name = "Skips",
            age = "10 weeks",
            breed = "Labrador",
            imageUrl = "https://i.pinimg.com/originals/d4/3f/75/d43f75fc3d2f128cf528a4802aafd6f2.jpg",
            about = Puppy.About(
                short = "Introducing the sweetheart, Skips, from our \"Regular Show Litter\" \uD83D\uDC95",
                long = """
                        Introducing the sweetheart, Skips, from our "Regular Show Litter" 💕

                        Skips is the biggest sweetheart, he is so gentle and sweet natured 😊 He wants nothing more then to cuddle and be with his humans. Skips is playful too, but he definitely prefers to just cuddle and receive pats from any human thats's willing 🥰

                        He is great with other dogs, big and small, though still a puppy so still learning some manners like not jumping up at their faces. Skips is also good with cats, they're strange creatures apparently 🤣 Skips is great with kids, he is learning not to jump up and he is picking it up quickly. He has also learnt to "sit" on command as well 😍 Skips picked it up very quickly, he is a smart boy 💕
                    """.trimIndent()
            ),
            fee = 600,
            health = Puppy.Health(
                deSexed = false,
                vaccinated = false,
                wormed = true
            ),
            location = Puppy.Location(
                lat = -37.80750194214424,
                lng = 144.9473032693956
            )
        ),
        Puppy(
            id = "4",
            name = "Popcorn",
            age = "8 weeks",
            breed = "Mastiff Mix",
            imageUrl = "https://i.redd.it/eplo5g0i7fy21.jpg",
            about = Puppy.About(
                short = "IPopcorn like his litter mates is a very cruisy gentle little guy",
                long = """
                        Popcorn like his litter mates is a very cruisy gentle little guy. Popcorn is fond of cuddle and is happy to hanging out with his foster humans and other foster siblings. Pop will fit into any loving home either with other pets or as a single pet to be showered with all the love.
                        
                        Popcorn would like adopters who will continue his training to help him become these best boy he can be
                    """.trimIndent()
            ),
            fee = 500,
            health = Puppy.Health(
                deSexed = false,
                vaccinated = true,
                wormed = true
            ),
            location = Puppy.Location(
                lat = -37.80682381232435,
                lng = 145.0032648801739
            )
        ),
        Puppy(
            id = "5",
            name = "Odie",
            age = "2 months",
            breed = "Bull Arab Mix",
            imageUrl = "https://dogsaholic.com/wp-content/uploads/2018/03/cute-bull-arab-puppy-standing.jpg",
            about = Puppy.About(
                short = "Hi my name is Odie. I’m a 4.5 year old male Bull Arab x. I’m a medium to large size boy and weigh roughly 25kg. Dog Rescue Newcastle are helping my owners find a new home for me.",
                long = """
                        Hi my name is Odie. I’m a 4.5 year old male Bull Arab x. I’m a medium to large size boy and weigh roughly 25kg. Dog Rescue Newcastle are helping my owners find a new home for me.

                        I have had a couple of homes already and did spend some time in the pound, so I am looking for a home that is committed to providing me a long term home and one that can commit to meeting my mental and exercise needs.
                    """.trimIndent()
            ),
            fee = 300,
            health = Puppy.Health(
                deSexed = true,
                vaccinated = true,
                wormed = true
            ),
            location = Puppy.Location(
                lat = -37.84050635175383,
                lng = 144.99147049281925
            )
        ),
        Puppy(
            id = "6",
            name = "Sapphire",
            age = "3 months",
            breed = "Greyhound",
            imageUrl = "https://i.redd.it/181okqixf6wy.jpg",
            about = Puppy.About(
                short = "This gem is Sapphire, and she is a kind and gentle 2yo greyhound looking for her forever home!",
                long = """
                        This gem is Sapphire, and she is a kind and gentle 2yo greyhound looking for her forever home!

                        Sapphire is an affectionate girl with lots of love to give. She has enjoyed the company of everyone that she has met and is happy to spend the day by your side. She loves playing fetch, running around in the backyard, and having a nice gentle walk around the neighbourhood. However, she spends most of the day lounging around!
                    """.trimIndent()
            ),
            fee = 600,
            health = Puppy.Health(
                deSexed = false,
                vaccinated = false,
                wormed = false
            ),
            location = Puppy.Location(
                lat = -37.8507032115301,
                lng = 145.00413344220146
            )
        ),
        Puppy(
            id = "7",
            name = "Fide",
            age = "1 month",
            breed = "Border Collie Mix",
            imageUrl = "https://res.cloudinary.com/petrescue/image/upload/a_0/c_crop,w_1082,h_1082,x_143,y_595/c_fill,w_638,h_638/v1614485961/vyxjfxonkf88gptofszl.jpg",
            about = Puppy.About(
                short = "I am a six and a half year old Bordercollie x and Im looking for the ONE!",
                long = """
                        Hi ,I'm Fide,
                        I am a six and a half year old Bordercollie x and Im looking for the ONE!
                        I like other dogs but I don’t really want to share my new owner(s) with them and I don’t like cats .
                        I am uncertain around larger dogs and eventually get annoyed with small yappers .
                        A large block or acreage would be ideal as I like to run free .
                    """.trimIndent()
            ),
            fee = 200,
            health = Puppy.Health(
                deSexed = true,
                vaccinated = false,
                wormed = false
            ),
            location = Puppy.Location(
                lat = -37.84050635175383,
                lng = 144.99147049281925
            )
        ),
    )

    fun puppies(): Flow<List<Puppy>> {
        return flow {
            delay(1000)
            emit(puppies)
        }
    }
}
