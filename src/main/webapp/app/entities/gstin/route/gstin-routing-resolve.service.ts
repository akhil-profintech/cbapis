import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IGstin } from '../gstin.model';
import { GstinService } from '../service/gstin.service';

export const gstinResolve = (route: ActivatedRouteSnapshot): Observable<null | IGstin> => {
  const id = route.params['id'];
  if (id) {
    return inject(GstinService)
      .find(id)
      .pipe(
        mergeMap((gstin: HttpResponse<IGstin>) => {
          if (gstin.body) {
            return of(gstin.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default gstinResolve;
