import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
        position => {
          this.geolocationPosition = position;
          this.currentLat = position.coords.latitude;
          this.currentLong = position.coords.longitude;
        });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      this._http.get('https://api.edamam.com/search?app_id=00ea7c3e&app_key=f0aa9f9f7e9b21c16250d317054a28f2&from=0&to=5&q='
          + this.recipeValue)
          .subscribe((data: any) => {
              const items = data.hits; // accessing recipe (hits[i]->)
              for (let i = 0 ; i <= items.length; i++) { // iterate through the list which data.hits has and push to recipeList
                  this.recipeList.push(items[i].recipe);
              }
              // console.log(this.recipeList);
          });
    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      this._http.get('https://api.foursquare.com/v2/venues/explore?client_id=34YIGNPKOFYNCWWKBRAT541KNYQVDYGDCDILNM5VLPKDXTHR&client_secret=R5FN0SMVEPXER5OFMNYVK3WK12QJRBGTRJWHP3CRV5X0L5GL&v=20180323&limit=9&near=' + this.placeValue + '&query='
          + this.recipeValue)
          .subscribe((data: any) => {
            const items = data.response.groups[0].items; // accessing venue (response->groups[0]->items[i]->)
            for (let i = 0 ; i <= items.length; i++) { // iterate through the list which items has and push to recipeList
              this.venueList.push(items[i].venue);
            }
            // console.log(this.venueList);
          });
    }
  }
}
