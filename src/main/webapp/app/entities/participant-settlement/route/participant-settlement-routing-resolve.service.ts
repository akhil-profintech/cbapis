import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IParticipantSettlement } from '../participant-settlement.model';
import { ParticipantSettlementService } from '../service/participant-settlement.service';

export const participantSettlementResolve = (route: ActivatedRouteSnapshot): Observable<null | IParticipantSettlement> => {
  const id = route.params['id'];
  if (id) {
    return inject(ParticipantSettlementService)
      .find(id)
      .pipe(
        mergeMap((participantSettlement: HttpResponse<IParticipantSettlement>) => {
          if (participantSettlement.body) {
            return of(participantSettlement.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default participantSettlementResolve;
