import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICollectionTransactionDetails } from '../collection-transaction-details.model';
import { CollectionTransactionDetailsService } from '../service/collection-transaction-details.service';

export const collectionTransactionDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | ICollectionTransactionDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(CollectionTransactionDetailsService)
      .find(id)
      .pipe(
        mergeMap((collectionTransactionDetails: HttpResponse<ICollectionTransactionDetails>) => {
          if (collectionTransactionDetails.body) {
            return of(collectionTransactionDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default collectionTransactionDetailsResolve;
