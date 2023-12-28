import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRequestOffer } from '../request-offer.model';
import { RequestOfferService } from '../service/request-offer.service';

export const requestOfferResolve = (route: ActivatedRouteSnapshot): Observable<null | IRequestOffer> => {
  const id = route.params['id'];
  if (id) {
    return inject(RequestOfferService)
      .find(id)
      .pipe(
        mergeMap((requestOffer: HttpResponse<IRequestOffer>) => {
          if (requestOffer.body) {
            return of(requestOffer.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default requestOfferResolve;
