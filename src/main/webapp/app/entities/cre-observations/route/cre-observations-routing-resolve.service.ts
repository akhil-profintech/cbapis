import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICREObservations } from '../cre-observations.model';
import { CREObservationsService } from '../service/cre-observations.service';

export const cREObservationsResolve = (route: ActivatedRouteSnapshot): Observable<null | ICREObservations> => {
  const id = route.params['id'];
  if (id) {
    return inject(CREObservationsService)
      .find(id)
      .pipe(
        mergeMap((cREObservations: HttpResponse<ICREObservations>) => {
          if (cREObservations.body) {
            return of(cREObservations.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default cREObservationsResolve;
