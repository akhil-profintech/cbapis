import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDocDetails } from '../doc-details.model';
import { DocDetailsService } from '../service/doc-details.service';

export const docDetailsResolve = (route: ActivatedRouteSnapshot): Observable<null | IDocDetails> => {
  const id = route.params['id'];
  if (id) {
    return inject(DocDetailsService)
      .find(id)
      .pipe(
        mergeMap((docDetails: HttpResponse<IDocDetails>) => {
          if (docDetails.body) {
            return of(docDetails.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default docDetailsResolve;
