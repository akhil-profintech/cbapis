import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFundsTransfer, NewFundsTransfer } from '../funds-transfer.model';

export type PartialUpdateFundsTransfer = Partial<IFundsTransfer> & Pick<IFundsTransfer, 'id'>;

export type EntityResponseType = HttpResponse<IFundsTransfer>;
export type EntityArrayResponseType = HttpResponse<IFundsTransfer[]>;

@Injectable({ providedIn: 'root' })
export class FundsTransferService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/funds-transfers');

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService,
  ) {}

  create(fundsTransfer: NewFundsTransfer): Observable<EntityResponseType> {
    return this.http.post<IFundsTransfer>(this.resourceUrl, fundsTransfer, { observe: 'response' });
  }

  update(fundsTransfer: IFundsTransfer): Observable<EntityResponseType> {
    return this.http.put<IFundsTransfer>(`${this.resourceUrl}/${this.getFundsTransferIdentifier(fundsTransfer)}`, fundsTransfer, {
      observe: 'response',
    });
  }

  partialUpdate(fundsTransfer: PartialUpdateFundsTransfer): Observable<EntityResponseType> {
    return this.http.patch<IFundsTransfer>(`${this.resourceUrl}/${this.getFundsTransferIdentifier(fundsTransfer)}`, fundsTransfer, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFundsTransfer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFundsTransfer[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFundsTransferIdentifier(fundsTransfer: Pick<IFundsTransfer, 'id'>): number {
    return fundsTransfer.id;
  }

  compareFundsTransfer(o1: Pick<IFundsTransfer, 'id'> | null, o2: Pick<IFundsTransfer, 'id'> | null): boolean {
    return o1 && o2 ? this.getFundsTransferIdentifier(o1) === this.getFundsTransferIdentifier(o2) : o1 === o2;
  }

  addFundsTransferToCollectionIfMissing<Type extends Pick<IFundsTransfer, 'id'>>(
    fundsTransferCollection: Type[],
    ...fundsTransfersToCheck: (Type | null | undefined)[]
  ): Type[] {
    const fundsTransfers: Type[] = fundsTransfersToCheck.filter(isPresent);
    if (fundsTransfers.length > 0) {
      const fundsTransferCollectionIdentifiers = fundsTransferCollection.map(
        fundsTransferItem => this.getFundsTransferIdentifier(fundsTransferItem)!,
      );
      const fundsTransfersToAdd = fundsTransfers.filter(fundsTransferItem => {
        const fundsTransferIdentifier = this.getFundsTransferIdentifier(fundsTransferItem);
        if (fundsTransferCollectionIdentifiers.includes(fundsTransferIdentifier)) {
          return false;
        }
        fundsTransferCollectionIdentifiers.push(fundsTransferIdentifier);
        return true;
      });
      return [...fundsTransfersToAdd, ...fundsTransferCollection];
    }
    return fundsTransferCollection;
  }
}
