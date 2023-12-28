import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVANumber } from '../va-number.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../va-number.test-samples';

import { VANumberService } from './va-number.service';

const requireRestSample: IVANumber = {
  ...sampleWithRequiredData,
};

describe('VANumber Service', () => {
  let service: VANumberService;
  let httpMock: HttpTestingController;
  let expectedResult: IVANumber | IVANumber[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VANumberService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a VANumber', () => {
      const vANumber = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(vANumber).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a VANumber', () => {
      const vANumber = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(vANumber).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a VANumber', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of VANumber', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a VANumber', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVANumberToCollectionIfMissing', () => {
      it('should add a VANumber to an empty array', () => {
        const vANumber: IVANumber = sampleWithRequiredData;
        expectedResult = service.addVANumberToCollectionIfMissing([], vANumber);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vANumber);
      });

      it('should not add a VANumber to an array that contains it', () => {
        const vANumber: IVANumber = sampleWithRequiredData;
        const vANumberCollection: IVANumber[] = [
          {
            ...vANumber,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVANumberToCollectionIfMissing(vANumberCollection, vANumber);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a VANumber to an array that doesn't contain it", () => {
        const vANumber: IVANumber = sampleWithRequiredData;
        const vANumberCollection: IVANumber[] = [sampleWithPartialData];
        expectedResult = service.addVANumberToCollectionIfMissing(vANumberCollection, vANumber);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vANumber);
      });

      it('should add only unique VANumber to an array', () => {
        const vANumberArray: IVANumber[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const vANumberCollection: IVANumber[] = [sampleWithRequiredData];
        expectedResult = service.addVANumberToCollectionIfMissing(vANumberCollection, ...vANumberArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const vANumber: IVANumber = sampleWithRequiredData;
        const vANumber2: IVANumber = sampleWithPartialData;
        expectedResult = service.addVANumberToCollectionIfMissing([], vANumber, vANumber2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vANumber);
        expect(expectedResult).toContain(vANumber2);
      });

      it('should accept null and undefined values', () => {
        const vANumber: IVANumber = sampleWithRequiredData;
        expectedResult = service.addVANumberToCollectionIfMissing([], null, vANumber, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vANumber);
      });

      it('should return initial array if no VANumber is added', () => {
        const vANumberCollection: IVANumber[] = [sampleWithRequiredData];
        expectedResult = service.addVANumberToCollectionIfMissing(vANumberCollection, undefined, null);
        expect(expectedResult).toEqual(vANumberCollection);
      });
    });

    describe('compareVANumber', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVANumber(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareVANumber(entity1, entity2);
        const compareResult2 = service.compareVANumber(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareVANumber(entity1, entity2);
        const compareResult2 = service.compareVANumber(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareVANumber(entity1, entity2);
        const compareResult2 = service.compareVANumber(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
