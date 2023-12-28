import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVANumber, NewVANumber } from '../va-number.model';

export type PartialUpdateVANumber = Partial<IVANumber> & Pick<IVANumber, 'id'>;

export type EntityResponseType = HttpResponse<IVANumber>;
export type EntityArrayResponseType = HttpResponse<IVANumber[]>;

@Injectable({ providedIn: 'root' })
export class VANumberService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/va-numbers');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(vANumber: NewVANumber): Observable<EntityResponseType> {
    return this.http.post<IVANumber>(this.resourceUrl, vANumber, { observe: 'response' });
  }

  update(vANumber: IVANumber): Observable<EntityResponseType> {
    return this.http.put<IVANumber>(`${this.resourceUrl}/${this.getVANumberIdentifier(vANumber)}`, vANumber, { observe: 'response' });
  }

  partialUpdate(vANumber: PartialUpdateVANumber): Observable<EntityResponseType> {
    return this.http.patch<IVANumber>(`${this.resourceUrl}/${this.getVANumberIdentifier(vANumber)}`, vANumber, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVANumber>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVANumber[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVANumberIdentifier(vANumber: Pick<IVANumber, 'id'>): number {
    return vANumber.id;
  }

  compareVANumber(o1: Pick<IVANumber, 'id'> | null, o2: Pick<IVANumber, 'id'> | null): boolean {
    return o1 && o2 ? this.getVANumberIdentifier(o1) === this.getVANumberIdentifier(o2) : o1 === o2;
  }

  addVANumberToCollectionIfMissing<Type extends Pick<IVANumber, 'id'>>(
    vANumberCollection: Type[],
    ...vANumbersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const vANumbers: Type[] = vANumbersToCheck.filter(isPresent);
    if (vANumbers.length > 0) {
      const vANumberCollectionIdentifiers = vANumberCollection.map(vANumberItem => this.getVANumberIdentifier(vANumberItem)!);
      const vANumbersToAdd = vANumbers.filter(vANumberItem => {
        const vANumberIdentifier = this.getVANumberIdentifier(vANumberItem);
        if (vANumberCollectionIdentifiers.includes(vANumberIdentifier)) {
          return false;
        }
        vANumberCollectionIdentifiers.push(vANumberIdentifier);
        return true;
      });
      return [...vANumbersToAdd, ...vANumberCollection];
    }
    return vANumberCollection;
  }
}
