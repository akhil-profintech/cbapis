import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IUpdateVA, NewUpdateVA } from '../update-va.model';

export type PartialUpdateUpdateVA = Partial<IUpdateVA> & Pick<IUpdateVA, 'id'>;

export type EntityResponseType = HttpResponse<IUpdateVA>;
export type EntityArrayResponseType = HttpResponse<IUpdateVA[]>;

@Injectable({ providedIn: 'root' })
export class UpdateVAService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/update-vas');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(updateVA: NewUpdateVA): Observable<EntityResponseType> {
    return this.http.post<IUpdateVA>(this.resourceUrl, updateVA, { observe: 'response' });
  }

  update(updateVA: IUpdateVA): Observable<EntityResponseType> {
    return this.http.put<IUpdateVA>(`${this.resourceUrl}/${this.getUpdateVAIdentifier(updateVA)}`, updateVA, { observe: 'response' });
  }

  partialUpdate(updateVA: PartialUpdateUpdateVA): Observable<EntityResponseType> {
    return this.http.patch<IUpdateVA>(`${this.resourceUrl}/${this.getUpdateVAIdentifier(updateVA)}`, updateVA, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUpdateVA>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUpdateVA[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getUpdateVAIdentifier(updateVA: Pick<IUpdateVA, 'id'>): number {
    return updateVA.id;
  }

  compareUpdateVA(o1: Pick<IUpdateVA, 'id'> | null, o2: Pick<IUpdateVA, 'id'> | null): boolean {
    return o1 && o2 ? this.getUpdateVAIdentifier(o1) === this.getUpdateVAIdentifier(o2) : o1 === o2;
  }

  addUpdateVAToCollectionIfMissing<Type extends Pick<IUpdateVA, 'id'>>(
    updateVACollection: Type[],
    ...updateVASToCheck: (Type | null | undefined)[]
  ): Type[] {
    const updateVAS: Type[] = updateVASToCheck.filter(isPresent);
    if (updateVAS.length > 0) {
      const updateVACollectionIdentifiers = updateVACollection.map(updateVAItem => this.getUpdateVAIdentifier(updateVAItem)!);
      const updateVASToAdd = updateVAS.filter(updateVAItem => {
        const updateVAIdentifier = this.getUpdateVAIdentifier(updateVAItem);
        if (updateVACollectionIdentifiers.includes(updateVAIdentifier)) {
          return false;
        }
        updateVACollectionIdentifiers.push(updateVAIdentifier);
        return true;
      });
      return [...updateVASToAdd, ...updateVACollection];
    }
    return updateVACollection;
  }
}
