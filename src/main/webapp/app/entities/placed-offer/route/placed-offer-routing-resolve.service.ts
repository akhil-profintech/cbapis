import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPlacedOffer } from '../placed-offer.model';
import { PlacedOfferService } from '../service/placed-offer.service';

export const placedOfferResolve = (route: ActivatedRouteSnapshot): Observable<null | IPlacedOffer> => {
  const id = route.params['id'];
  if (id) {
    return inject(PlacedOfferService)
      .find(id)
      .pipe(
        mergeMap((placedOffer: HttpResponse<IPlacedOffer>) => {
          if (placedOffer.body) {
            return of(placedOffer.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default placedOfferResolve;
