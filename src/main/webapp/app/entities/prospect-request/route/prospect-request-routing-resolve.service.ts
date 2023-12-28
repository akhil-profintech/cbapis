import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IProspectRequest } from '../prospect-request.model';
import { ProspectRequestService } from '../service/prospect-request.service';

export const prospectRequestResolve = (route: ActivatedRouteSnapshot): Observable<null | IProspectRequest> => {
  const id = route.params['id'];
  if (id) {
    return inject(ProspectRequestService)
      .find(id)
      .pipe(
        mergeMap((prospectRequest: HttpResponse<IProspectRequest>) => {
          if (prospectRequest.body) {
            return of(prospectRequest.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default prospectRequestResolve;
