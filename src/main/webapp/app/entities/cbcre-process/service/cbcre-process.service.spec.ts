import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICBCREProcess } from '../cbcre-process.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../cbcre-process.test-samples';

import { CBCREProcessService } from './cbcre-process.service';

const requireRestSample: ICBCREProcess = {
  ...sampleWithRequiredData,
};

describe('CBCREProcess Service', () => {
  let service: CBCREProcessService;
  let httpMock: HttpTestingController;
  let expectedResult: ICBCREProcess | ICBCREProcess[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CBCREProcessService);
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

    it('should create a CBCREProcess', () => {
      const cBCREProcess = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(cBCREProcess).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CBCREProcess', () => {
      const cBCREProcess = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(cBCREProcess).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CBCREProcess', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CBCREProcess', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CBCREProcess', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCBCREProcessToCollectionIfMissing', () => {
      it('should add a CBCREProcess to an empty array', () => {
        const cBCREProcess: ICBCREProcess = sampleWithRequiredData;
        expectedResult = service.addCBCREProcessToCollectionIfMissing([], cBCREProcess);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cBCREProcess);
      });

      it('should not add a CBCREProcess to an array that contains it', () => {
        const cBCREProcess: ICBCREProcess = sampleWithRequiredData;
        const cBCREProcessCollection: ICBCREProcess[] = [
          {
            ...cBCREProcess,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCBCREProcessToCollectionIfMissing(cBCREProcessCollection, cBCREProcess);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CBCREProcess to an array that doesn't contain it", () => {
        const cBCREProcess: ICBCREProcess = sampleWithRequiredData;
        const cBCREProcessCollection: ICBCREProcess[] = [sampleWithPartialData];
        expectedResult = service.addCBCREProcessToCollectionIfMissing(cBCREProcessCollection, cBCREProcess);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cBCREProcess);
      });

      it('should add only unique CBCREProcess to an array', () => {
        const cBCREProcessArray: ICBCREProcess[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const cBCREProcessCollection: ICBCREProcess[] = [sampleWithRequiredData];
        expectedResult = service.addCBCREProcessToCollectionIfMissing(cBCREProcessCollection, ...cBCREProcessArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cBCREProcess: ICBCREProcess = sampleWithRequiredData;
        const cBCREProcess2: ICBCREProcess = sampleWithPartialData;
        expectedResult = service.addCBCREProcessToCollectionIfMissing([], cBCREProcess, cBCREProcess2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cBCREProcess);
        expect(expectedResult).toContain(cBCREProcess2);
      });

      it('should accept null and undefined values', () => {
        const cBCREProcess: ICBCREProcess = sampleWithRequiredData;
        expectedResult = service.addCBCREProcessToCollectionIfMissing([], null, cBCREProcess, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cBCREProcess);
      });

      it('should return initial array if no CBCREProcess is added', () => {
        const cBCREProcessCollection: ICBCREProcess[] = [sampleWithRequiredData];
        expectedResult = service.addCBCREProcessToCollectionIfMissing(cBCREProcessCollection, undefined, null);
        expect(expectedResult).toEqual(cBCREProcessCollection);
      });
    });

    describe('compareCBCREProcess', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCBCREProcess(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCBCREProcess(entity1, entity2);
        const compareResult2 = service.compareCBCREProcess(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCBCREProcess(entity1, entity2);
        const compareResult2 = service.compareCBCREProcess(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCBCREProcess(entity1, entity2);
        const compareResult2 = service.compareCBCREProcess(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
