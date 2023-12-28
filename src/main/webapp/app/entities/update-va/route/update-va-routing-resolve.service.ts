import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IUpdateVA } from '../update-va.model';
import { UpdateVAService } from '../service/update-va.service';

export const updateVAResolve = (route: ActivatedRouteSnapshot): Observable<null | IUpdateVA> => {
  const id = route.params['id'];
  if (id) {
    return inject(UpdateVAService)
      .find(id)
      .pipe(
        mergeMap((updateVA: HttpResponse<IUpdateVA>) => {
          if (updateVA.body) {
            return of(updateVA.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default updateVAResolve;
