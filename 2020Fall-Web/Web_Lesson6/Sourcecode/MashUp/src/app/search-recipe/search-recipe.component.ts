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
      /**
       * Write code to get place
       */
    }

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') {
      this._http.get('https://api.foursquare.com/v2/venues/explore?client_id=34YIGNPKOFYNCWWKBRAT541KNYQVDYGDCDILNM5VLPKDXTHR&client_secret=R5FN0SMVEPXER5OFMNYVK3WK12QJRBGTRJWHP3CRV5X0L5GL&v=20180323&limit=15&ll=40.7243,-74.0018&query=')
          .subscribe((data: any) => {
            this.venueList = Object.keys(data.venue).map(function (k) {
              const i = data.venue[k];
              return {
                title: i.title, body: i.extract
              };
              console.log(this.venueList);
            });
          });
    }
  }
}
