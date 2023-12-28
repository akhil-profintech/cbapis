import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IParticipantSettlement, NewParticipantSettlement } from '../participant-settlement.model';

export type PartialUpdateParticipantSettlement = Partial<IParticipantSettlement> & Pick<IParticipantSettlement, 'id'>;

export type EntityResponseType = HttpResponse<IParticipantSettlement>;
export type EntityArrayResponseType = HttpResponse<IParticipantSettlement[]>;

@Injectable({ providedIn: 'root' })
export class ParticipantSettlementService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/participant-settlements');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(participantSettlement: NewParticipantSettlement): Observable<EntityResponseType> {
    return this.http.post<IParticipantSettlement>(this.resourceUrl, participantSettlement, { observe: 'response' });
  }

  update(participantSettlement: IParticipantSettlement): Observable<EntityResponseType> {
    return this.http.put<IParticipantSettlement>(
      `${this.resourceUrl}/${this.getParticipantSettlementIdentifier(participantSettlement)}`,
      participantSettlement,
      { observe: 'response' },
    );
  }

  partialUpdate(participantSettlement: PartialUpdateParticipantSettlement): Observable<EntityResponseType> {
    return this.http.patch<IParticipantSettlement>(
      `${this.resourceUrl}/${this.getParticipantSettlementIdentifier(participantSettlement)}`,
      participantSettlement,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IParticipantSettlement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IParticipantSettlement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getParticipantSettlementIdentifier(participantSettlement: Pick<IParticipantSettlement, 'id'>): number {
    return participantSettlement.id;
  }

  compareParticipantSettlement(o1: Pick<IParticipantSettlement, 'id'> | null, o2: Pick<IParticipantSettlement, 'id'> | null): boolean {
    return o1 && o2 ? this.getParticipantSettlementIdentifier(o1) === this.getParticipantSettlementIdentifier(o2) : o1 === o2;
  }

  addParticipantSettlementToCollectionIfMissing<Type extends Pick<IParticipantSettlement, 'id'>>(
    participantSettlementCollection: Type[],
    ...participantSettlementsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const participantSettlements: Type[] = participantSettlementsToCheck.filter(isPresent);
    if (participantSettlements.length > 0) {
      const participantSettlementCollectionIdentifiers = participantSettlementCollection.map(
        participantSettlementItem => this.getParticipantSettlementIdentifier(participantSettlementItem)!,
      );
      const participantSettlementsToAdd = participantSettlements.filter(participantSettlementItem => {
        const participantSettlementIdentifier = this.getParticipantSettlementIdentifier(participantSettlementItem);
        if (participantSettlementCollectionIdentifiers.includes(participantSettlementIdentifier)) {
          return false;
        }
        participantSettlementCollectionIdentifiers.push(participantSettlementIdentifier);
        return true;
      });
      return [...participantSettlementsToAdd, ...participantSettlementCollection];
    }
    return participantSettlementCollection;
  }
}
