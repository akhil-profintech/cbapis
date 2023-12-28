import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAcceptedOffer } from '../accepted-offer.model';
import { AcceptedOfferService } from '../service/accepted-offer.service';

export const acceptedOfferResolve = (route: ActivatedRouteSnapshot): Observable<null | IAcceptedOffer> => {
  const id = route.params['id'];
  if (id) {
    return inject(AcceptedOfferService)
      .find(id)
      .pipe(
        mergeMap((acceptedOffer: HttpResponse<IAcceptedOffer>) => {
          if (acceptedOffer.body) {
            return of(acceptedOffer.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default acceptedOfferResolve;
